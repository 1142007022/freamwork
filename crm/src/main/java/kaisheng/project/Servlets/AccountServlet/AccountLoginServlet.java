package kaisheng.project.servlets.AccountServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.exception.ServiceException;
import kaisheng.project.service.AccountService;
import kaisheng.project.utils.Config;
import kaisheng.project.utils.Result;

@WebServlet("/login")
public class AccountLoginServlet extends SuperServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @param checked
	 * @param mobile
	 * @param req
	 * @param resp
	 */
	public void Cookie(String checked, String mobile, HttpServletRequest req, HttpServletResponse resp) {
		if (StringUtils.isNotEmpty(checked)) {
			Cookie cookie = new Cookie("mobile", mobile);
			cookie.setHttpOnly(true);
			cookie.setMaxAge(60 * 60 * 60 * 60);
			cookie.setDomain("19.168.1.73");
			cookie.setPath("/");
			resp.addCookie(cookie);
		} else {
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if ("mobile".equals(cookie.getName())) {
					cookie.setDomain("localhost");
					cookie.setPath("/");
					cookie.setMaxAge(0);

					resp.addCookie(cookie);
				}
			}
		}
	}

	/**
	 * 检测前端页面是否打勾
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	public List<String> isRemember(HttpServletRequest req, HttpServletResponse resp) {
		String checked = "";
		String username = "";
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("mobile".equals(cookie.getName())) {
					username = cookie.getValue();
					checked = "checked";
					break;
				}
			}
		}


		List<String> lists = new ArrayList<>();
		lists.add(checked);
		lists.add(username);
		return lists;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String checked = isRemember(req, resp).get(0);
		String username = isRemember(req, resp).get(1);
		req.setAttribute("checked", checked);
		req.setAttribute("username", username);
		jump("account/login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mobile = req.getParameter("username");
		String password = req.getParameter("password");
		String checked = req.getParameter("remember");
		// System.out.println("checked--------"+checked);
		AccountService service = new AccountService();
		Map<String, Object> maps = new HashMap<>();

		try {
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(3600);
			Account acc = service.findByMobileAndPassword(mobile, password);
			int adminId = Integer.parseInt(Config.get("adminId"));
			String adminMobile = Config.get("adminMobile");
			String adminPassword = Config.get("adminPassword");

			if(acc != null){
				if (acc.getId() == adminId && acc.getMobile().equals(adminMobile)
						&& acc.getPassword().equals(adminPassword)) {
					session.setAttribute("isAdmin", "yes");
				}
				String name = acc.getUsername();
				session.setAttribute("account", acc);
				maps.put("state", "success");
				maps.put("data", acc);
				// req.setAttribute("account", acc);
				Cookie(checked, mobile, req, resp);
				Logger logger =  LoggerFactory.getLogger(AccountLoginServlet.class);
				logger.info("{}登录",name);
				sendJson(maps, resp);
			}else{
				Result res = Result.error("用户名不存在！");
				sendJson(res, resp);
			}
		} catch (ServiceException e) {
			maps.put("state", "error");
			maps.put("messages", e.getMessage());
			Cookie(checked, mobile, req, resp);
			sendJson(maps, resp);
		}
	}

}
