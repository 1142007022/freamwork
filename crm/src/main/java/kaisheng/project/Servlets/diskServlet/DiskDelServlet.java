package kaisheng.project.servlets.diskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.service.DiskService;
import kaisheng.project.utils.Result;

@WebServlet("/disk/del")
public class DiskDelServlet extends SuperServlet{
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println("id--"+id);
		getLogger().info("{}删除文件夹{}",getAccName(req),service.findDiskById(Integer.parseInt(id)).getName());
		service.delById(id);
		/*service.delBypId(id);*/
		Result res = Result.success(null);
		sendJson(res, resp);
	}
}
