package kaisheng.project.servlets.diskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.service.DiskService;

@WebServlet("/disk/add")
public class DiskAddServlet extends SuperServlet{
	
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		String name = req.getParameter("name");
		int accId = getAcc(req).getId();
		Logger logger = getLogger();
		String accName = getAccName(req);
		logger.info("{}添加文件夹{}",accName,name);
		service.addDisk(pid,accId,name);
	}
}
