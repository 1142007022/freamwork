package kaisheng.project.servlets.saleChance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Account;
import kaisheng.project.entitys.Customer;
import kaisheng.project.service.AccountService;
import kaisheng.project.service.CustomerService;
import kaisheng.project.service.SaleChanceService;
import kaisheng.project.utils.Config;
import kaisheng.project.utils.Result;

@WebServlet("/sale/add")
public class AddSalechanceServlet extends SuperServlet{
	
	AccountService accService = new AccountService();
	SaleChanceService saleChanceService = new SaleChanceService();
	CustomerService service = new CustomerService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Account acc = (Account) session.getAttribute("account");
		int id = acc.getId();
		List<Customer> custLists = service.findCustNumByAccouId(id);
		
		List<String> process = getMessgaes();
		req.setAttribute("customerList", custLists);
		req.setAttribute("process", process);
		
		jump("sale/addSaleChance", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("salename");
		int custId = Integer.parseInt(req.getParameter("custId"));
	    float worth = Float.parseFloat(req.getParameter("worth"));
		String process = req.getParameter("process");    //当前进度
		String content = req.getParameter("content");
		int accountId = getAcc(req).getId();
		
		String accName = getAcc(req).getUsername();
		
		saleChanceService.saveSaleChance(name,custId,worth,process,content,accountId);
		
		Logger logger = LoggerFactory.getLogger(AddSalechanceServlet.class);
		logger.info("{}添加{}机会",accName,name);
		
		Result res = Result.success(null);
		
		sendJson(res, resp);
	}
	
	public List<String> getMessgaes() {
		List<String> lists = new ArrayList<String>();
		String[] values = Config.get("sales.progress").split(",");
		for(int i = 0;i < values.length;i++){
			lists.add(values[i]);
		}
		return lists;
	}
}
