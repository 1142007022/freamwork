package kaisheng.project.servlets.taskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.service.TaskService;

@WebServlet("/task/changeStatus")
public class ChangeStatusServlet extends SuperServlet{
	
	TaskService service = new TaskService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		String status = req.getParameter("status");
		
		String statu = "未完成";
		if(status.equals("1")) {
			statu = "已完成";
		}
		
		getLogger().info("{}修改{}状态为{}",getAccName(req),service.findDiskById(taskId).getName(),statu);
		service.changeStatus(taskId,status);
	}
}
