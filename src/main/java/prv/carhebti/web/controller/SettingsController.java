package prv.carhebti.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import prv.carhebti.business.dao.CarManager;
import prv.carhebti.business.dao.SettingsManager;
import prv.carhebti.business.entities.Car;
import prv.carhebti.business.entities.Settings;
import prv.carhebti.business.entities.User;
import prv.carhebti.common.tools.ConversionTool;
import prv.carhebti.common.tools.WebTool;

/**
 * Servlet implementation class SettingsController
 */
@WebServlet("/settings/*")
public class SettingsController extends InitContext {
	
	private static final long serialVersionUID = 1L;
    
	private static final Logger log = Logger.getLogger(SettingsController.class);
	
	// Resources
	private static final String JSP_VIEW = "/pages/settings.jsp";
	private static final String TO_LIST = "page.do";
	private static final String UPDATE = "updatedSettings";

	// Managers
	private SettingsManager settingsManager = SettingsManager.SINGLETON;
	private CarManager carManager = CarManager.SINGLETON;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsController() {
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
   	 * Servlet mapping is <b>webapp</b>/settings/<b>method</b>.do <br/>
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
   		User owner = WebTool.getUser(request);
   		if (owner == null)
			throw new ServletException("No User");
   		
   		Settings settings = settingsManager.retrieveSingle(owner);
   		List<Car> cars = carManager.retrieve(owner);
   		
   		request.setAttribute("cars", cars);
   		request.setAttribute("settings", settings);
   		
   		RequestDispatcher rd = request.getRequestDispatcher(JSP_VIEW);
   		rd.forward(request, response);
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
		
		Settings settings = createBean(request);

		if (settings.getId() == null) {
			throw new ServletException("No User");
		} else {
			settingsManager.update(settings);
			updateSessionSettings(request.getSession(), settings);
		}

		request.getSession(). setAttribute(UPDATE, settings.getId());
		response.sendRedirect(TO_LIST);
	}
	
	private void updateSessionSettings(HttpSession session, Settings settings) {
		session.setAttribute("language", settings.getLanguage());
		session.setAttribute("settings", settings);
	}

	/**
	 * Create Service bean from Form Data
	 * @param request
	 * @return
	 */
	private Settings createBean(HttpServletRequest request) {
		// Get form-data
		Integer carId = ConversionTool.toInteger(request.getParameter("formSettings-car"));
		String language = request.getParameter("formSettings-language");
		
		// convert to Type
		Car car = carManager.retrieve(carId);
		User user = WebTool.getUser(request);
		Settings settings = settingsManager.retrieveSingle(user);
		settings.setCar(car);
		settings.setLanguage(language);
		
		return settings;
	}
}
