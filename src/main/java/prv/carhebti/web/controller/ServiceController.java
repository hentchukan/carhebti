package prv.carhebti.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import prv.carhebti.business.dao.CarManager;
import prv.carhebti.business.dao.ServiceManager;
import prv.carhebti.business.dao.TypeManager;
import prv.carhebti.business.entities.Car;
import prv.carhebti.business.entities.Service;
import prv.carhebti.business.entities.Type;
import prv.carhebti.business.entities.User;
import prv.carhebti.common.tools.ConversionTool;
import prv.carhebti.common.tools.WebTool;

/**
 * Servlet implementation class ServiceController
 */
@WebServlet({ "/service/*" })
public class ServiceController extends InitContext {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ServiceController.class);
	
	// Attributes
	private static final String DELETE = "deletedService";
	private static final String UPDATE = "updatedService";
	private static final String CREATION = "createdService";
	
	private static final String SERVICE_LIST = "services";
	private static final String TYPE_LIST = "types";
	private static final String CAR_LIST = "cars";
	
	// Redirect
	private static final String TO_LIST = "list.do";
	// Resources
	private static final String JSP_VIEW = "/pages/listServices.jsp";

	// Managers
	private ServiceManager serviceManager = ServiceManager.SINGLETON;
	private TypeManager typeManager = TypeManager.SINGLETON;
	private CarManager carManager = CarManager.SINGLETON;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceController() {
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
     * return List of services.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = WebTool.getUser(request);
		if (user == null)
			throw new ServletException("No User");
		
		request.setAttribute(SERVICE_LIST, serviceManager.retrieve());
		String accept = request.getHeader("accept");
		
		if (accept.contains("html")) {
			request.setAttribute(TYPE_LIST, typeManager.retrieve(user));
			request.setAttribute(CAR_LIST, carManager.retrieve(user));
			WebTool.swipeSessionAttributes(request, CREATION, UPDATE, DELETE);
			RequestDispatcher rd = request.getRequestDispatcher(JSP_VIEW);
			rd.forward(request, response);
		} else if (accept.contains("json")) {
			String serviceList = ConversionTool.toJson(serviceManager.retrieve(user));
			response.getWriter().println(serviceList);
		}
	}
	
	/**
	 * save a service (create or update) and return it's Id to ServiceList page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = WebTool.getUser(request);
		if (user == null)
			throw new ServletException("No User");
		
		String direction = null;
		Service service = createBean(request);

		if (service.getId() == null) {
			service = serviceManager.create(service);
			direction = CREATION;
		} else {
			serviceManager.update(service);
			direction = UPDATE;
		}

		request.getSession(). setAttribute(direction, service.getId());
		response.sendRedirect(TO_LIST);
	}
	
	/**
	 * delete a service, return its Id to ServiceList page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = WebTool.getUser(request);
		if (user == null)
			throw new ServletException("No User");
		
		Integer id = ConversionTool.toInteger(request.getParameter("formService-id"));
		if (id == null) {
			// TODO return 404 not found
			log.error("No item to delete "+id);
		} else {
			boolean result = serviceManager.delete(id);
			if (result) {
				request.getSession().setAttribute(DELETE, id);
				response.sendRedirect(TO_LIST);
			}
		}
	}

	/**
	 * Create Service bean from Form Data
	 * @param request
	 * @return
	 */
	private Service createBean(HttpServletRequest request) {
		// Get form-data
		Integer id = ConversionTool.toInteger(request.getParameter("formService-id"));
		Integer typeId = ConversionTool.toInteger(request.getParameter("formService-type"));
		Integer carId = ConversionTool.toInteger(request.getParameter("formService-car"));
		Date date = ConversionTool.toDate(request.getParameter("formService-date"));
		String provider = request.getParameter("formService-provider");
		String comment = request.getParameter("formService-comment");
		BigDecimal odometer = ConversionTool.toBigDecimal(request.getParameter("formService-odometer"));
		BigDecimal quantity = ConversionTool.toBigDecimal(request.getParameter("formService-quantity"));
		BigDecimal cost = ConversionTool.toBigDecimal(request.getParameter("formService-cost"));
		
		// convert to Type
		Type type = typeManager.retrieve(typeId);
		Car car = carManager.retrieve(carId);
		User user = WebTool.getUser(request);
		return new Service(id, type, date, odometer, quantity, provider, comment, cost, user, car);
	}
	
	/**
	 * Servlet mapping is <b>webapp</b>/service/<b>method</b>.do <br/>
	 * Split and take the last part = <b>method</b>.do <br/>
	 * Delete <b>.do</b> and return <b>method</b>
	 * @param request
	 * @return method name to be executed
	 */
	private String getMethod(HttpServletRequest request) {
		return request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1].replace(".do", "");
	}
	
	
}
