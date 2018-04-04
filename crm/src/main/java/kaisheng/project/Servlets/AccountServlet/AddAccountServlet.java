package kaisheng.project.servlets.AccountServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.exception.ServiceException;
import kaisheng.project.service.AccountService;
import kaisheng.project.utils.Result;

@WebServlet("/account/add")
public class AddAccountServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String mobile = req.getParameter("mobile");
		String[] depIds = req.getParameterValues("deptId");
		System.out.println(username);
		System.out.println(password);
		System.out.println(mobile);
		System.out.println(depIds);
		
		
		
		AccountService service = new AccountService();
		
		try {
			service.saveAccountDept(username,password,mobile,depIds);
			Result res = Result.success(null);
			Logger logger = LoggerFactory.getLogger(AddAccountServlet.class);
			logger.info("管理员添加员工");
			sendJson(res, resp);
		} catch (ServiceException e) {
			Result res = new Result().error(e.getMessage());
			sendJson(res, resp);
		}
	}
}
