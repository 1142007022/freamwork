package kaisheng.project.servlets.DeptServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Dept;
import kaisheng.project.service.DeptService;


@WebServlet("/dept/list")
public class DeptListServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DeptService service = new DeptService();
		List<Dept> lists = service.findAll();
		sendJson(lists, resp);
	}
	
}
