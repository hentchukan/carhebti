package prv.carhebti.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDispatcher
 */
@WebServlet("*.go")
public class ServletDispatcher extends InitContext {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.service(request, response);
		
		String page = processUrl(request.getRequestURI());
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	private String processUrl(String requestURI) {
		String[] splited = requestURI.split("/");
		String servletContext = splited[splited.length - 1].replace(".go", "").replace(".do", "").replace("List", "/list").replace("Page", "/page");
		
		return servletContext ;
	}

}
