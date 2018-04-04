package kaisheng.project.servlets.CustomerServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.entitys.Customer;
import kaisheng.project.service.CustomerService;

@WebServlet("/customer/public/detail")
public class PublicDetailServlet extends SuperServlet{

	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("account");
		int custId = Integer.parseInt(id);
		Customer cust = service.findCustById(custId);
		
		req.setAttribute("account", acc);
		req.setAttribute("customer", cust);
		/*List<Account> lists = accService.findAll();
		req.setAttribute("accountList", lists);*/
		jump("customer/publicdetail", req, resp);
	}
}
