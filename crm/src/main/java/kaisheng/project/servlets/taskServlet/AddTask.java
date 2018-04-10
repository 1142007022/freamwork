package kaisheng.project.servlets.taskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.service.TaskService;

@WebServlet("/task/add")
public class AddTask extends SuperServlet{

	TaskService service = new TaskService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jump("task/addTask", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String finishTime = req.getParameter("finishTime");
		int accId = getAcc(req).getId();
		getLogger().info("{}添加{}待办事项",getAccName(req),name);
		service.addTask(name,finishTime,accId);
	}
}
