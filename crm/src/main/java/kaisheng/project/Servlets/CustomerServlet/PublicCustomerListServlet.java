package kaisheng.project.servlets.CustomerServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Customer;
import kaisheng.project.service.CustomerService;
import kaisheng.project.utils.Page;
import kaisheng.project.utils.Result;

@WebServlet("/customer/public")
public class PublicCustomerListServlet extends SuperServlet{
    
	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		jump("customer/publicCustomerList", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int p = Integer.parseInt(req.getParameter("p"));
	//	int p = 1;
		Page<Customer> page = service.finaCustByPage(p);
		Result res = Result.success(page);
		sendJson(res, resp);
	}
}
