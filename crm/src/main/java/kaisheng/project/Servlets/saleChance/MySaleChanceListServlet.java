package kaisheng.project.servlets.saleChance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.SaleChance;
import kaisheng.project.service.SaleChanceService;
import kaisheng.project.utils.Page;


@WebServlet("/sale/my/list")
public class MySaleChanceListServlet extends SuperServlet{

	SaleChanceService service = new SaleChanceService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String p = req.getParameter("p");
		int pageNum = 1;
		
		if(StringUtils.isNotEmpty(p)) {
			pageNum = Integer.parseInt(p);
		}
		
		int accountId = getAcc(req).getId();
		
		Page<SaleChance> saleChancePage = service.findByPage(pageNum,accountId);
		
		req.setAttribute("page", saleChancePage);
		jump("sale/mySaleChanceList", req, resp);
	}
	
}
