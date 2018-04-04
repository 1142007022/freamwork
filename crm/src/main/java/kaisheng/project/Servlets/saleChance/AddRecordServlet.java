package kaisheng.project.servlets.saleChance;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.SaleChanceRecord;
import kaisheng.project.service.SaleChanceRecordService;

@WebServlet("/sale/add/record")
public class AddRecordServlet extends SuperServlet{

	SaleChanceRecordService service = new SaleChanceRecordService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("saleId");
		int saleId = Integer.parseInt(id);
		String content = req.getParameter("content");
		
		service.saveRecord(saleId,content);
		
		List<SaleChanceRecord> recordLists = service.findRecordById(saleId);
		
		/*req.setAttribute("recordList", recordLists);
		Result res = Result.success(recordLists);
		sendJson(res, resp);
		jump("sale/detail", req, resp);*/
	}
	
}
