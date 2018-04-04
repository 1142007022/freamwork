package kaisheng.project.servlets.AccountServlet;

import kaisheng.project.servlets.SuperServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/account/home")
public class AccountHomeServlet extends SuperServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jump("account/home", req, resp);
	}

}
