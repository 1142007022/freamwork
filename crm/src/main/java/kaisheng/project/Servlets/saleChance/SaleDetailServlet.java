package kaisheng.project.servlets.saleChance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.SaleChance;
import kaisheng.project.entitys.SaleChanceRecord;
import kaisheng.project.service.SaleChanceRecordService;
import kaisheng.project.service.SaleChanceService;
import kaisheng.project.utils.Config;

@WebServlet("/sale/detail")
public class SaleDetailServlet extends SuperServlet{
	
	SaleChanceRecordService recordService = new SaleChanceRecordService();
	SaleChanceService service = new SaleChanceService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("saleId");
		int saleID = Integer.parseInt(id);
		
		List<String> lists = getMessgaes();
		SaleChance salechance = service.findSaleChanceById(saleID);
		List<SaleChanceRecord> recordLists = recordService.findRecordById(saleID);
		
		/*for(int i = 0;i < recordLists.size();i++) {
			System.out.println("getContent------------"+recordLists.get(i).getContent());
		}*/
		
		
		req.setAttribute("recordLists", recordLists);
		req.setAttribute("processList", lists);
		req.setAttribute("saleChance", salechance);
		jump("sale/detail", req, resp);
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
