package kaisheng.project.servlets.AccountServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.exception.ServiceException;
import kaisheng.project.service.AccountService;
import kaisheng.project.utils.Result;

@WebServlet("/changeMyMessages")
public class ChangeMessagesServlet extends SuperServlet{

	AccountService service = new AccountService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jump("account/changeKey", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accId = req.getParameter("accId");
		String newPassword = req.getParameter("newPassword");
		String newPassword1 = req.getParameter("newPassword1");
		int id = Integer.parseInt(accId);
	/*	System.out.println(newPassword);
		System.out.println(newPassword1);
		*/
		
		if(newPassword.equals(newPassword1)){
			
			try {
				service.changeKey(newPassword,id);
				Result res = Result.success(null);
				sendJson(res, resp);
			} catch (ServiceException e) {
				Result res = Result.error(e.getMessage());
				sendJson(res, resp);
			}
			
		} else {
			Result res = Result.error("两次输入的密码不一致请检查后重试");
			sendJson(res, resp);
		}
		
	}
}
