package kaisheng.project.servlets.CustomerServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Customer;
import kaisheng.project.service.CustomerService;

@WebServlet("/customer/public/edit")
public class EditPublicServlet extends SuperServlet{

	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		int custId = Integer.parseInt(id);
		List<List<String>> lists = service.getMessgaes();
		Customer cust = service.findCustById(custId);
		
	    req.setAttribute("sources", lists.get(0));
	    req.setAttribute("trades", lists.get(1));
		req.setAttribute("customer", cust);
		jump("customer/editPublic", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String custId = req.getParameter("custId");
		String name = req.getParameter("custname");
		String sex = req.getParameter("sex");
		String job = req.getParameter("jobtitle");
		String address = req.getParameter("address");
		String mobile = req.getParameter("mobile");
		String source = req.getParameter("source");
		String trade = req.getParameter("trade");
		String level = req.getParameter("level");
		String mark = req.getParameter("mark");
		
		service.edit(custId,name,sex,job,address,mobile,source,trade,level,mark);
	}
	
}
