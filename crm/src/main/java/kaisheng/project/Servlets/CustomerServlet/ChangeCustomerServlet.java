package kaisheng.project.servlets.CustomerServlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.service.AccountService;
import kaisheng.project.service.CustomerService;

@WebServlet("/customer/my/change")
public class ChangeCustomerServlet extends SuperServlet {

	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;
	Logger logger = getLogger();
	AccountService accService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String toAccountId = req.getParameter("toAccountId");
		String custId = req.getParameter("custId");
		int id = Integer.parseInt(custId);
		String accName = getAccName(req);
		String type = req.getParameter("type");

		/*
		 * System.out.println("custId--------->"+custId);
		 * System.out.println("toAccountId--------->"+toAccountId);
		 * System.out.println("type--------->"+type);
		 */
		if (StringUtils.isNotEmpty(toAccountId)) {
			// 转交他人
			int accountId = Integer.parseInt(toAccountId);
			Account toAcc = accService.findAccountById(accountId);
			Timestamp updateTime = new Timestamp(System.currentTimeMillis());
			HttpSession session = req.getSession();
			Account acc = (Account) session.getAttribute("account");
			String reminder = "由" + acc.getUsername() + "转让";
			logger.info("由{}转交给{}", accName, toAcc.getUsername());

			service.changeCustomerToOthers(id, accountId, updateTime, reminder);
			// req.setAttribute("id", custId);

		} else {

			if (type.equals("del")) {
				// 删除操作
				service.delCustById(id);
				logger.info("{}删除", accName);
			} else {
				// 放入公海操作
				service.changCustToPublicById(id);
				logger.info("{}放入公海", accName);
			}

		}
		
		resp.sendRedirect("/customer/my");
		
	}

}
