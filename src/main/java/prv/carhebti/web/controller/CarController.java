package prv.carhebti.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import prv.carhebti.business.dao.CarManager;
import prv.carhebti.business.entities.Car;
import prv.carhebti.business.entities.User;
import prv.carhebti.common.tools.ConversionTool;
import prv.carhebti.common.tools.WebTool;

/**
 * Servlet implementation class CarController
 */
@WebServlet("/car/*")
public class CarController extends InitContext {
	
	private static final long serialVersionUID = 1L;
    
	private CarManager carManager = CarManager.SINGLETON;
	private static final Logger log = Logger.getLogger(CarController.class);
	
	// Attributes
	private static final String DELETE = "deletedCar";
	private static final String UPDATE = "updatedCar";
	private static final String CREATION = "createdCar";
	
	private static final String CAR_LIST = "cars";
	
	// Redirect
	private static final String TO_LIST = "list.do";
	// Resources
	private static final String JSP_VIEW = "../pages/listCars.jsp";
	
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
     * return List of cars.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = WebTool.getUser(request);
		if (user == null)
			throw new ServletException("No User");
		
		request.setAttribute(CAR_LIST, carManager.retrieve(user));
		String accept = request.getHeader("accept");
		
		if (accept.contains("html")) {
			request.setAttribute(CAR_LIST, carManager.retrieve(user));
			WebTool.swipeSessionAttributes(request, CREATION, UPDATE, DELETE);
			RequestDispatcher rd = request.getRequestDispatcher(JSP_VIEW);
			rd.forward(request, response);
		} else if (accept.contains("json")) {
			String carList = ConversionTool.toJson(carManager.retrieve());
			response.getWriter().println(carList);
		}
	}
	
	/**
	 * save a car (create or update) and return it's Id to CarList page
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
		Car car = createBean(request);

		if (car.getId() == null) {
			car = carManager.create(car);
			direction = CREATION;
		} else {
			carManager.update(car);
			direction = UPDATE;
		}

		request.getSession(). setAttribute(direction, car.getId());
		response.sendRedirect(TO_LIST);
	}
	
	/**
	 * delete a car, return its Id to CarList page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = WebTool.getUser(request);
		if (user == null)
			throw new ServletException("No User");
		
		Integer id = ConversionTool.toInteger(request.getParameter("formCar-id"));
		if (id == null) {
			// TODO return 404 not found
			log.error("No item to delete "+id);
		} else {
			boolean result = carManager.delete(id);
			if (result) {
				request.getSession().setAttribute(DELETE, id);
				response.sendRedirect(TO_LIST);
			}
		}
	}
	
	/**
	 * Servlet mapping is <b>webapp</b>/car/<b>method</b>.do <br/>
	 * Split and take the last part = <b>method</b>.do <br/>
	 * Delete <b>.do</b> and return <b>method</b>
	 * @param request
	 * @return method name to be executed
	 */
	private String getMethod(HttpServletRequest request) {
		return request.getRequestURI().split("/")[request.getRequestURI().split("/").length - 1].replace(".do", "");
	}
	
	private Car createBean(HttpServletRequest request) {
		Integer id = ConversionTool.toInteger(request.getParameter("formCar-id"));
		String manifacturer = request.getParameter("formCar-manifacturer");
		String trade = request.getParameter("formCar-trade");
		String number = request.getParameter("formCar-number");
		String greyCard = request.getParameter("formCar-greyCard");
		User user = WebTool.getUser(request);
		
		return new Car(id, manifacturer, trade, number, greyCard, user);
	}
	
	

}
