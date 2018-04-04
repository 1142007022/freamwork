package kaisheng.project.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;


public class EncoingFilter extends SuperFilter{

	String encoding = "UTF-8";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		String encoding = config.getInitParameter("encoding");
		if(StringUtils.isNotEmpty(encoding)) {
			this.encoding = encoding;
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	
		
	}

}
