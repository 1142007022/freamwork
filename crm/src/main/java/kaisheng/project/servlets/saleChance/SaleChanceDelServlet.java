package kaisheng.project.servlets.saleChance;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.SaleChance;
import kaisheng.project.service.SaleChanceService;

@WebServlet("/sale/del")
public class SaleChanceDelServlet extends SuperServlet{
	
	SaleChanceService service = new SaleChanceService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("saleId");
		int saleId = Integer.parseInt(id);
		SaleChance chance = service.findSaleChanceById(saleId);
		String saleName = chance.getName();
		String accName = getAcc(req).getUsername();
		service.delSaleChanceById(saleId);
		Logger logger = getLogger();
		logger.info("{}删除{}机会",accName,saleName);
		resp.sendRedirect("/sale/my/list");
	}
}
