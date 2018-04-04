package kaisheng.project.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kaisheng.project.entitys.Account;

public class LoginFilter extends SuperFilter{
	
	List<String> lists = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String src = config.getInitParameter("valitade");
		String[] srcs = src.split(",");
		lists = Arrays.asList(srcs);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String url = req.getRequestURI();
		
		if(URL(url)){
			
			HttpSession session = req.getSession();
			Account acc = (Account)session.getAttribute("account");
			if(acc == null){
				url = getUriWithParam(url, req);
				resp.sendRedirect("/login?callback=" + url);
			} else {
				chain.doFilter(req, resp);
			}
			
		}else {
			chain.doFilter(req, resp);
		}
		
		
		
	}
	
	public boolean URL(String url) {

		Boolean flag = true;

		if(lists == null){
			flag = false;
		}else {
			for (int i = 0; i < lists.size(); i++) {
				if (url.startsWith(lists.get(i))) {
					flag = true;
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}
	
	
	
	private String getUriWithParam(String uri, HttpServletRequest req) {
		Map<String,String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		Iterator<String> it = keys.iterator();
		if(it.hasNext()) {
			uri += "?";
			while(it.hasNext()) {
				String key = it.next();
				String[] values = params.get(key);
				for(String value : values) {
					String param = key + "=" + value +"&"; // id=1&id=2&
					uri += param;
				}
			}
			uri = uri.substring(0, uri.length()-1); // uri?id=12&name=jack  12345  (0,4)
		}
		return uri;
	}


}
