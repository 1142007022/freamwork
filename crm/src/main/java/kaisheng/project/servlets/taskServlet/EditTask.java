package kaisheng.project.servlets.taskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Task;
import kaisheng.project.service.TaskService;

@WebServlet("/edit/task")
public class EditTask extends SuperServlet{
	
	TaskService service = new TaskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		Task task = service.getTaskById(taskId);
		req.setAttribute("task", task);
		jump("task/edit", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("id");
		String name = req.getParameter("name");
		String finishTime = req.getParameter("finishTime");
		getLogger().info("{}修改{}事项",getAccName(req),name);
		service.edit(taskId,name,finishTime);
	}
}
