package kaisheng.project.servlets.diskServlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.service.DiskService;
import kaisheng.project.utils.Result;

@WebServlet("/disk/upload")
@MultipartConfig
public class DiskUploadServlet extends SuperServlet{
	
	DiskService service = new DiskService();
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");
		Part part = req.getPart("file");
		InputStream Input = part.getInputStream();
		String md5 = req.getParameter("fileMd5");
		String name = req.getParameter("name");
		long fiileSize = part.getSize();
		int accId = getAcc(req).getId();
		service.saveFile(Input,name,pid,fiileSize,accId,md5);
		getLogger().info("{}上传文件{}",getAccName(req),name);
		Result res = Result.success(null);
		sendJson(res, resp);
	}
}
