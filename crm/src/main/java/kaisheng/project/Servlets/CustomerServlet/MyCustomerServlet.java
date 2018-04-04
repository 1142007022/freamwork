package kaisheng.project.servlets.CustomerServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.entitys.Customer;
import kaisheng.project.service.CustomerService;
import kaisheng.project.utils.Page;
import kaisheng.project.utils.Result;

@WebServlet("/customer/my")

public class MyCustomerServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;

	CustomerService service = new CustomerService();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jump("customer/MyCustomer", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int pageNum = 1;
		String p = req.getParameter("p");
		if(StringUtils.isNumeric(p)) {
			pageNum = Integer.parseInt(p);
		}
//		System.out.println("p--------------->"+p);
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("account");
		int accountId = acc.getId();
		
		Page<Customer> page = service.findByPage(pageNum,accountId);
		req.setAttribute("pages", page);
		Result res = Result.success(page);
		
		sendJson(res, resp);
		
	}
	
}
