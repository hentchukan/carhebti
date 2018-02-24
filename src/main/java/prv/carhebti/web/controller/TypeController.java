package prv.carhebti.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import prv.carhebti.business.dao.TypeManager;
import prv.carhebti.business.entities.Type;
import prv.carhebti.common.tools.ConversionTool;

/**
 * Servlet implementation class ListTypesController
 */
@WebServlet({ "/type/*" })
public class TypeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private TypeManager typeManager = TypeManager.SINGLETON;
	private static final Logger log = Logger.getLogger(TypeController.class);
	
	// Attributes
	private static final String DELETE = "deletedType";
	private static final String UPDATE = "updatedType";
	private static final String CREATION = "createdType";
	
	private static final String TYPE_LIST = "types";
	
	// Redirect
	private static final String TO_LIST = "list.do";
	// Resources
	private static final String JSP_VIEW = "../pages/listTypes.jsp";
	
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
     * return List of types.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute(TYPE_LIST, typeManager.retrieve());
		String accept = request.getHeader("accept");
		
		if (accept.contains("html")) {
			request.setAttribute(TYPE_LIST, typeManager.retrieve());
			swipeSessionAttributes(request);
			RequestDispatcher rd = request.getRequestDispatcher(JSP_VIEW);
			rd.forward(request, response);
		} else if (accept.contains("json")) {
			String serviceList = ConversionTool.toJson(typeManager.retrieve());
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
		Type type = createBean(request);

		if (type.getId() == null) {
			type = typeManager.create(type);
			direction = CREATION;
		} else {
			typeManager.update(type);
			direction = UPDATE;
		}

		request.getSession(). setAttribute(direction, type.getId());
		response.sendRedirect(TO_LIST);
	}
	
	/**
	 * delete a type, return its Id to TypeList page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = ConversionTool.toInteger(request.getParameter("formType-id"));
		if (id == null) {
			// TODO return 404 not found
			log.error("No item to delete "+id);
		} else {
			boolean result = typeManager.delete(id);
			if (result) {
				request.getSession().setAttribute(DELETE, id);
				response.sendRedirect(TO_LIST);
			}
		}
	}
	
	/**
	 * Servlet mapping is <b>webapp</b>/type/<b>method</b>.do <br/>
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
	
	private Type createBean(HttpServletRequest request) {
		Integer id = ConversionTool.toInteger(request.getParameter("formType-id"));
		String name = request.getParameter("formType-name");
		String provider = request.getParameter("formType-provider");
		Boolean odometer = Boolean.valueOf(request.getParameter("formType-odometer") != null );
		Boolean quantity = Boolean.valueOf(request.getParameter("formType-quantity") != null);
		
		return new Type(id, name, provider, odometer, quantity);
	}
}
