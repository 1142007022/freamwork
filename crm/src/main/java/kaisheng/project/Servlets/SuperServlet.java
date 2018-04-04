package kaisheng.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import kaisheng.project.entitys.Account;

public class SuperServlet extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(SuperServlet.class);
	
	public String getAccName(HttpServletRequest req) {
		return getAcc(req).getUsername();
	}
	
	public Logger getLogger() {
		return logger;
	}
	private static final long serialVersionUID = 1L;

	public void jump(String url, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/" + url + ".jsp").forward(req, resp);
	}
	
	public Account getAcc(HttpServletRequest req){
		HttpSession session = req.getSession();
		Account acc = (Account) session.getAttribute("account");
		return acc;
	}
	
	public void sendJson(Object obj, HttpServletResponse resp) throws IOException{
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		String jon = new Gson().toJson(obj);
		out.print(jon);
		out.flush();
		out.close();
	}
		
}
