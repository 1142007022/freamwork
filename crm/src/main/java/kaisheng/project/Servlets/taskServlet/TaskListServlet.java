package kaisheng.project.servlets.taskServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Task;
import kaisheng.project.service.TaskService;

@WebServlet("/task")
public class TaskListServlet extends SuperServlet{

	TaskService service = new TaskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int accId = getAcc(req).getId();
		String show = req.getParameter("show");
		List<Task> lists = service.findTasksByAccId(accId,show);
		req.setAttribute("taskList", lists);
		jump("task/taskList", req, resp);
	}
}
