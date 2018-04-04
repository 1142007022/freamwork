package kaisheng.project.servlets.DeptServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Dept;
import kaisheng.project.exception.ServiceException;
import kaisheng.project.service.DeptService;
import kaisheng.project.utils.Result;
@WebServlet("/dept/add")
public class DeptAddServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jump("account/list", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptName = req.getParameter("deptName");
		DeptService service = new DeptService();
		try {
			Dept dept = service.addDept(deptName);
			Result res = Result.success(dept);
			sendJson(res, resp);
		} catch (ServiceException e) {
			Result res = Result.error(e.getMessage());
			sendJson(res, resp);
		}
	}
}
