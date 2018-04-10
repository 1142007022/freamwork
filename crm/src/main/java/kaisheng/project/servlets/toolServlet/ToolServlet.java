package kaisheng.project.servlets.toolServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kaisheng.project.servlets.SuperServlet;

@WebServlet("/jisuanqi")
public class ToolServlet extends SuperServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jump("tool/machine", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String equation = req.getParameter("equation");
		System.out.println("equation------" + equation);
		String[] equations = equation.split(" ");

		List<String> numList = new ArrayList<String>();
		List<String> signList = new ArrayList<String>();

		for (int i = 0; i < equations.length; i++) {
			if (i % 2 == 0) {
				numList.add(equations[i]);
			} else {
				signList.add(equations[i]);
			}
		}

		for (int i = 0; i < numList.size(); i++) {
			System.out.println("numList------" + numList.get(i));
		}
		List<List<String>> lists = chengchu(numList, signList);
		List<String> numListJiaJian = lists.get(0);
		List<String> signListJiaJian = lists.get(1);
		float res = jiajian(numListJiaJian, signListJiaJian);
		sendJson(res, resp);

	}

	private float jiajian(List<String> numListJiaJian, List<String> signListJiaJian) {
		float latRes = 0;

		if (signListJiaJian.indexOf("+") != -1 || signListJiaJian.indexOf("-") != -1) {
			for (int i = 0; i < signListJiaJian.size(); i++) {
				if (signListJiaJian.get(i).equals("+")) {
					float numA = Float.parseFloat(numListJiaJian.get(i));
					float numB = Float.parseFloat(numListJiaJian.get(i + 1));
					String res = String.valueOf(numA + numB);

					numListJiaJian.set(i, res);
					numListJiaJian.remove(i + 1);
					signListJiaJian.remove(i);

					jiajian(numListJiaJian, signListJiaJian);
				} else {
					float numA = Float.parseFloat(numListJiaJian.get(i));
					float numB = Float.parseFloat(numListJiaJian.get(i + 1));
					String res = String.valueOf(numA - numB);

					numListJiaJian.set(i, res);
					numListJiaJian.remove(i + 1);
					signListJiaJian.remove(i);

					jiajian(numListJiaJian, signListJiaJian);
				}
			}
		}
		latRes = Float.parseFloat(numListJiaJian.get(0));

		return latRes;
	}

	public List<List<String>> chengchu(List<String> numList, List<String> signList) {
		List<List<String>> lists = new ArrayList<List<String>>();
		if (signList.indexOf("*") != -1 || signList.indexOf("/") != -1 || signList.indexOf("%") != -1) {

			for (int i = 0; i < signList.size(); i++) {
				if (signList.get(i).equals("*")) {
					float numA = Float.parseFloat(numList.get(i));
					float numB = Float.parseFloat(numList.get(i + 1));
					String res = String.valueOf(numA * numB);

					numList.set(i, res);
					numList.remove(i + 1);
					signList.remove(i);
					for (int p = 0; p < numList.size(); p++) {
						System.out.println("numList----" + numList.get(p));
					}
					for (int p = 0; p < signList.size(); p++) {
						System.out.println("signList----" + signList.get(p));
					}
					chengchu(numList, signList);
				} else if (signList.get(i).equals("/")) {

					float numA = Float.parseFloat(numList.get(i));
					float numB = Float.parseFloat(numList.get(i + 1));
					String res = String.valueOf(numA / numB);

					numList.set(i, res);
					numList.remove(i + 1);
					signList.remove(i);

					chengchu(numList, signList);

				} else if (signList.get(i).equals("%")) {

					float numA = Float.parseFloat(numList.get(i));
					float numB = Float.parseFloat(numList.get(i + 1));
					String res = String.valueOf(numA % numB);

					numList.set(i, res);
					numList.remove(i + 1);
					signList.remove(i);

					chengchu(numList, signList);
				}
			}

		}

		lists.add(numList);
		lists.add(signList);

		return lists;
	}
}
