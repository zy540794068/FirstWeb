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

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest){
			HttpServletRequest hrequest = (HttpServletRequest) request;
			HttpServletResponse hresponse = (HttpServletResponse) response;
			if(hrequest.getRequestURI().indexOf("login")==-1&& hrequest.getSession(false).getAttribute("student") == null){
				hresponse.getWriter().print("not login");
				((HttpServletResponse)response).setStatus(401);
				response.flushBuffer();
				return;
			}
		}
		filter.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
