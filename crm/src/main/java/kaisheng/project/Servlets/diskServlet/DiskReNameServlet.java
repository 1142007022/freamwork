package kaisheng.project.servlets.diskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.service.DiskService;
@WebServlet("/disk/rename")
public class DiskReNameServlet extends SuperServlet{
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"),"utf-8");
		getLogger().info("{}为文件/文件夹重命名为{}",getAccName(req),name);
		service.reName(id,name);
	}

}
