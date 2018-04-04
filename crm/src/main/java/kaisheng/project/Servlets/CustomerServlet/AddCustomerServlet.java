package kaisheng.project.servlets.CustomerServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.exception.ServiceException;
import kaisheng.project.service.CustomerService;
import kaisheng.project.utils.Result;

@WebServlet("/customer/my/add")

public class AddCustomerServlet extends SuperServlet {

	private static final long serialVersionUID = 1L;
	CustomerService service = new CustomerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<List<String>> lists = service.getMessgaes();
		req.setAttribute("sources", lists.get(0));
		req.setAttribute("trades", lists.get(1));

		jump("customer/AddCustomer", req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String job = req.getParameter("job");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		String trade = req.getParameter("trade");
		String level = req.getParameter("level");
		String mark = req.getParameter("mark");
		String source = req.getParameter("source");
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("account");
		int acoountId = acc.getId();

		//获取当前登录的accountName
		String accName  = getAcc(req).getUsername();
		
		try {
			service.saveCustomer(name, sex, job, mobile, address, trade, source, level, mark, acoountId);
			
			//生成日志   由哪位员工添加了谁
			Logger logger = getLogger();
			logger.info("{}添加顾客{}",accName,name);
			
			Result res = Result.success(null);
			sendJson(res, resp);

		} catch (ServiceException e) {

			Result res = Result.error(e.getMessage());
			sendJson(res, resp);

		}

	}

}
