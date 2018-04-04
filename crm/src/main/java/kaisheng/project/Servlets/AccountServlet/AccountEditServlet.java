package kaisheng.project.servlets.AccountServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import kaisheng.project.entitys.Account;
import kaisheng.project.entitys.Dept;
import kaisheng.project.service.AccountService;
import kaisheng.project.service.DeptService;
import kaisheng.project.utils.Result;

@WebServlet("/account/edit")
public class AccountEditServlet extends SuperServlet {
	
	DeptService deptService =  new DeptService();
	AccountService service = new AccountService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		int accId = Integer.parseInt(id);
		//System.out.println("accid===================="+accId);
		
		//此处根据id会查到多条数据 只是deptName不同 应该在service中将deptName更新 将后面的deptName更新到第一个中去  将多个对象合并成一个对象 再返回
		Account acc = service.finEditAccById(accId);
		
		//System.out.println("accdeptname------------"+acc.getDeptName());
		
		List<Dept> deptLists = deptService.findAllChildDept();
		req.setAttribute("account", acc);
		req.setAttribute("depts", deptLists);
		//sendJson(deptLists, resp);
		jump("account/edit", req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String[] deptid = req.getParameterValues("deptId");
		String username = req.getParameter("username");
		String mobile = req.getParameter("mobile");
		
		/*System.out.println("id--------------"+id);
		System.out.println("deptid--------------"+deptid);
		System.out.println("username--------------"+username);
		System.out.println("mobile--------------"+mobile);*/
		
		service.editAccount(username,mobile,Integer.parseInt(id));
		service.delAccAnddeptByAccId(id);
		
		for(int i = 0;i < deptid.length;i++) {
			service.saveAccountAndDept(id,deptid[i]);
		}
		
		Result res = Result.success(null);
		Logger logger = LoggerFactory.getLogger(AccountEditServlet.class);
		logger.info("{}修改个人信息",username);
		sendJson(res, resp);
	}
	
}
