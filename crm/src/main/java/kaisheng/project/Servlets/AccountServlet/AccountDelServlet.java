package kaisheng.project.servlets.AccountServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.service.AccountService;
import kaisheng.project.utils.Result;

@WebServlet("/account/del")
public class AccountDelServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;
	AccountService service = new AccountService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		int accId = Integer.parseInt(id);
		Account acc = service.findAccountById(accId);
		String name = acc.getUsername();
		service.delById(accId);
		
		Result res = Result.success(null);
		
		Logger logger = LoggerFactory.getLogger(AccountDelServlet.class);
		logger.info("管理员删除{}",name);
		sendJson(res, resp);
	}
}

