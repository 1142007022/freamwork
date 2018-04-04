package kaisheng.project.servlets.chartServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.service.SaleChanceService;
import kaisheng.project.utils.Result;

@WebServlet("/chart/schedule")
public class ScheduleServlet extends SuperServlet{
	
	SaleChanceService service = new SaleChanceService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jump("charts/schedule", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Map<String,Object>> lists = service.getSaleChanceCountAndschedule();
		Result res = Result.success(lists);
		sendJson(res, resp);
	}
}
