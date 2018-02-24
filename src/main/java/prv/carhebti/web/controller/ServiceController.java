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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import prv.carhebti.business.dao.ServiceManager;
import prv.carhebti.business.dao.TypeManager;
import prv.carhebti.business.entities.Service;
import prv.carhebti.business.entities.Type;
import prv.carhebti.common.tools.ConversionTool;

/**
 * Servlet implementation class ServiceController
 */
@WebServlet({ "/service/*" })
public class ServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ServiceController.class);
	
	// Attributes
	private static final String DELETE = "deletedService";
	private static final String UPDATE = "updatedService";
	private static final String CREATION = "createdService";
	
	private static final String SERVICE_LIST = "services";
	private static final String TYPE_LIST = "types";
	
	// Redirect
	private static final String TO_LIST = "list.do";
	// Resources
	private static final String JSP_VIEW = "/pages/listServices.jsp";

	// Managers
	private ServiceManager serviceManager = ServiceManager.SINGLETON;
	private TypeManager typeManager = TypeManager.SINGLETON;
	
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
		String methodName = getMothod(request);
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
		
		request.setAttribute(SERVICE_LIST, serviceManager.retrieve());
		String accept = request.getHeader("accept");
		
		if (accept.contains("html")) {
			request.setAttribute(TYPE_LIST, typeManager.retrieve());
			swipeSessionAttributes(request);
			RequestDispatcher rd = request.getRequestDispatcher(JSP_VIEW);
			rd.forward(request, response);
		} else if (accept.contains("json")) {
			String serviceList = ConversionTool.toJson(serviceManager.retrieve());
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
		String direction = null;
		Service service = createBean(request);

		if (service.getIdService() == null) {
			service = serviceManager.create(service);
			direction = CREATION;
		} else {
			serviceManager.update(service);
			direction = UPDATE;
		}

		request.getSession(). setAttribute(direction, service.getIdService());
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
		Date date = ConversionTool.toDate(request.getParameter("formService-date"));
		String provider = request.getParameter("formService-provider");
		String comment = request.getParameter("formService-comment");
		BigDecimal odometer = ConversionTool.toBigDecimal(request.getParameter("formService-odometer"));
		BigDecimal quantity = ConversionTool.toBigDecimal(request.getParameter("formService-quantity"));

		// convert to Type
		Type type = typeManager.retrieve(typeId);
		return new Service(id, type, date, odometer, quantity, provider, comment);
	}
	
	/**
	 * Servlet mapping is <b>webapp</b>/service/<b>method</b>.do <br/>
	 * Split and take the last part = <b>method</b>.do <br/>
	 * Delete <b>.do</b> and return <b>method</b>
	 * @param request
	 * @return method name to be executed
	 */
	private String getMothod(HttpServletRequest request) {
		return request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1].replace(".do", "");
	}
	
	private void swipeSessionAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if (session == null)
			return ;
		
		if (session.getAttribute(CREATION) != null) {
			request.setAttribute(CREATION, session.getAttribute(CREATION));
			session.removeAttribute(CREATION);
		}
		
		if (session.getAttribute(UPDATE) != null) {
			request.setAttribute(UPDATE, session.getAttribute(UPDATE));
			session.removeAttribute(UPDATE);
		}
		
		if (session.getAttribute(DELETE) != null) {
			request.setAttribute(DELETE, session.getAttribute(DELETE));
			session.removeAttribute(DELETE);
		}
	}
}
