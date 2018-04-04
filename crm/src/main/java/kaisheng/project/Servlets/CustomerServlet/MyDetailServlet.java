package kaisheng.project.servlets.CustomerServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.entitys.Customer;
import kaisheng.project.service.AccountService;
import kaisheng.project.service.CustomerService;

@WebServlet("/customer/my/detail")
public class MyDetailServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;
	CustomerService service = new CustomerService();
	AccountService accService = new AccountService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		//System.out.println("id===================="+id);
		int custId = Integer.parseInt(id);
		Customer cust = service.findCustById(custId);
		//System.out.println(cust.getName()+"---------name");
		req.setAttribute("customer", cust);
		List<Account> lists = accService.findAll();
		req.setAttribute("accountList", lists);
		jump("customer/mydetail", req, resp);
	}
}
