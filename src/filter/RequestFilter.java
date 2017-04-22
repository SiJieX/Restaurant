package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class RequestFilter
 */
public class RequestFilter implements Filter {


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		StringBuffer urlSb = req.getRequestURL();
		String url = urlSb.toString();
		String str = url.substring(url.lastIndexOf("/")+1, url.length());
		System.out.println(str);
		if("app".equals(str)||"sys".equals(str)||str.endsWith(".*")){
			chain.doFilter(req, resp);
			return ;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
