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

@WebServlet("/customer/add/public")
public class AddPublicCustServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;
	CustomerService service = new CustomerService();
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			HttpSession session = req.getSession();
			Account acc = (Account) session.getAttribute("account");
			
			int accId = acc.getId();
			List<List<String>> lists = service.getMessgaes();
			
            req.setAttribute("sources", lists.get(0));
            req.setAttribute("trades", lists.get(1));
			
			jump("customer/addPublicCust", req, resp);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");
			String job = req.getParameter("job");
			String address = req.getParameter("address");
			String mobile = req.getParameter("mobile");
			String source = req.getParameter("source");
			String trade = req.getParameter("trade");
			String level = req.getParameter("level");
			String mark = req.getParameter("mark");
			
			try {
				service.saveCustomer(name, sex, job, mobile, address, trade, source, level, mark, 0);
				Result res = Result.success(null);
				
				//生成日志
				String accName = getAccName(req);
				Logger logger = getLogger();
				logger.info("{}添加公海{}客户",accName,name);
				sendJson(res, resp);
			} catch (ServiceException e) {
				Result res = Result.error(e.getMessage());
				sendJson(res, resp);
			}
			
			
			
		}
}
