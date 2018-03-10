package prv.carhebti.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import prv.carhebti.business.dao.ServiceManager;
import prv.carhebti.business.dto.BudgetByTypeItem;
import prv.carhebti.common.tools.ConversionTool;

/**
 * Servlet implementation class BudgetController
 */
@WebServlet("/budget/*")
public class BudgetController extends InitContext {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(BudgetController.class);

	// Resources
	private static final String JSP_VIEW = "/pages/budget.jsp";
	
	// Managers
	private ServiceManager serviceManager = ServiceManager.SINGLETON;
	
	public BudgetController() {
		super();
	}
	
	/**
	 * Works on both doPost and doGet </br>
	 * Dispatches 
	 */
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	super.service(request, response);
    	
    	String methodName = getMethod(request);
		try {
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error("couldn't satisfy the operation " + methodName);
		}
	}
    
    /**
	 * Servlet mapping is <b>webapp</b>/budget/<b>method</b>.do <br/>
	 * Split and take the last part = <b>method</b>.do <br/>
	 * Delete <b>.do</b> and return <b>method</b>
	 * @param request
	 * @return method name to be executed
	 */
	private String getMethod(HttpServletRequest request) {
		return request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1].replace(".do", "");
	}
	
	/**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher rd = request.getRequestDispatcher(JSP_VIEW);
		rd.forward(request, response);
	}
	
	/**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void data(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accept = request.getHeader("accept");
		
		if (accept.contains("json")) {
			
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			
			Date startDate = ConversionTool.toDate(start, "ddMMyyyy");
			Date endDate = ConversionTool.toDate(end, "ddMMyyyy");
			
			List<BudgetByTypeItem> array = serviceManager.findTotalCostByType(startDate, endDate);
			
			String data = ConversionTool.toJson(array);
	        response.setContentType("application/json");
			response.getWriter().print(data);
		}
	}
}
