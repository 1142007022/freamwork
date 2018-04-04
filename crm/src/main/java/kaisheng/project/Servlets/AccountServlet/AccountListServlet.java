package kaisheng.project.servlets.AccountServlet;

import java.io.IOException;
import kaisheng.project.servlets.SuperServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;


import kaisheng.project.entitys.Account;
import kaisheng.project.service.AccountService;
import kaisheng.project.utils.Page;
import kaisheng.project.utils.Result;

@WebServlet("/account/list.json")
public class AccountListServlet extends SuperServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String deptId = req.getParameter("deptId");
		String p = req.getParameter("p");
		// System.out.println(deptId);
		int pNum = 1;
		if (StringUtils.isNumeric(p)) {
			pNum = Integer.parseInt(p);
		}
		AccountService service = new AccountService();
		
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("account");
		
		
		Page<Account> page = service.findByPage(deptId, pNum,req);
		// System.out.println(page.getTotalpage());
		
		req.setAttribute("account", acc);
		Result res = Result.success(page);
		sendJson(res, resp);
	}
}
