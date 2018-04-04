package kaisheng.project.servlets.taskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Task;
import kaisheng.project.service.TaskService;

@WebServlet("/task/del")
public class TaskDelServlet extends SuperServlet{
	
	TaskService service = new TaskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		Task task = service.findDiskById(taskId);
		getLogger().info("{}删除待办事项{}",getAccName(req),task.getName());
		service.delById(taskId);
	}
}
