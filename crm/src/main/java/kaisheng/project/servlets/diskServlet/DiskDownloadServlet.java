package kaisheng.project.servlets.diskServlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import kaisheng.project.servlets.SuperServlet;
import kaisheng.project.entitys.Disk;
import kaisheng.project.service.DiskService;
import kaisheng.project.utils.Config;

@WebServlet("/disk/download")
public class DiskDownloadServlet extends SuperServlet{
	
	DiskService service = new DiskService(); 
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String name = req.getParameter("fileName");
		Disk disk = service.findDiskById(Integer.parseInt(id));
		
		if(disk == null){
			resp.sendError(404, "参数异常");
		}else{
			
			String filePath = Config.get("file.upload.path");
			InputStream in = new FileInputStream(new File(filePath,disk.getSaveName()));
			OutputStream out = resp.getOutputStream();
			if(StringUtils.isNotEmpty(name)){
				//xiazai
				name = new String(name.getBytes("ISO8859-1"),"UTF-8");
				resp.setContentType("application/octet-stream");
				//name = new String(name.getBytes("ISO8859-1"),"UTF-8");
				resp.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
				getLogger().info("{}下载文件{}",getAccName(req),service.findDiskById(Integer.parseInt(id)).getName());
			}

			IOUtils.copy(in, out);
			out.flush();
			out.close();
			in.close();
			
		}
	}
	
}
