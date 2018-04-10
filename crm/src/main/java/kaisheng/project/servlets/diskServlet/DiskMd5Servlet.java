package kaisheng.project.servlets.diskServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Disk;
import kaisheng.project.service.DiskService;
import kaisheng.project.utils.Result;

@WebServlet("/disk/md5")
public class DiskMd5Servlet extends SuperServlet{
	
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String md5 = req.getParameter("md5");
		String pId = req.getParameter("pid");
		String name = req.getParameter("name");
		Disk disk = service.findDiskByMd5(md5);
		if(disk != null){
			name = new String(name.getBytes("ISO8859-1"),"UTF-8");
			disk.setName(name);
			disk.setMd5(md5);
			disk.setpId(Integer.parseInt(pId));
			service.addDisk(disk);
			Result res = Result.error("");
			sendJson(res, resp);
		}else{
			Result res = Result.success(null);
			sendJson(res, resp);
		}
	}
	
}
