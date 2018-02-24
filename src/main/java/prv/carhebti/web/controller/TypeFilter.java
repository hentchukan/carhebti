package prv.carhebti.web.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class TypeFilter
 */
@WebFilter("/type/*")
public class TypeFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) {
		      HttpServletRequest request = (HttpServletRequest) req;
		      HttpServletResponse response = (HttpServletResponse) resp;
		      
		      if (request.getRequestURI().contains(".do") || request.getRequestURI().contains(".go")) 
		    	  // pass the request along the filter chain
		    	  chain.doFilter(request, response);
		      else {
		    	  response.sendRedirect(request.getRequestURI().replace("/type/", "/"));
		      }
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	 /**
     * Default constructor. 
     */
    public TypeFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

}
