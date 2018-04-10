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

@WebServlet("/sale/process/update")
public class UpdateProcessServlet extends SuperServlet{
	
	SaleChanceRecordService recordService = new SaleChanceRecordService();
	SaleChanceService service = new SaleChanceService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String process = req.getParameter("process");
		String id = req.getParameter("saleId");
		int saleId = Integer.parseInt(id);
		
		
		
		List<SaleChanceRecord> recordLists = recordService.findRecordById(saleId);
		List<String> lists = getMessgaes();
		SaleChance salechance = service.UpdateProcessById(saleId,process);
		
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
