package kaisheng.project.servlets.diskServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Disk;
import kaisheng.project.service.DiskService;

@WebServlet("/disk/list")
public class DiskListServlet extends SuperServlet{
	
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		int AaccId = getAcc(req).getId();
		Disk disk = null;
		int pId = 0;
		List<Disk> lists = new ArrayList<>();
		if(pid == null){
			lists = service.getDiskListByPid(pId);
			
		}else {
			lists = service.getDiskListByPid(Integer.parseInt(pid));
			disk = new Disk();
			disk = service.findDiskById(Integer.parseInt(pid));
		}
		req.setAttribute("disk", disk);
		req.setAttribute("diskList", lists);
		jump("disk/list", req, resp);
	}
}
