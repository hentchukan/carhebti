package prv.carhebti.web.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prv.carhebti.business.dao.SettingsManager;
import prv.carhebti.business.dao.UserManager;
import prv.carhebti.business.entities.Settings;
import prv.carhebti.business.entities.User;

/**
 * Servlet implementation class InitContext Load User: Look for user in the
 * session If User exists, go to main page If User doesn't exist, load user by
 * session name. If User loaded, load settings too.
 * 
 */
@WebServlet("")
public class InitContext extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserManager userManager = UserManager.SINGLETON;
	private SettingsManager settingManager = SettingsManager.SINGLETON;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitContext() {
		super();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("user");
		if (user == null) {
			loadUser(req);
			loadSettings(req);
		}

		if (req.getPathInfo() != null && req.getPathInfo().matches("/")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, resp);
		}
	}

	/**
	 * Load user using parameter request "username".
	 * 
	 * @param request
	 * @throws ServletException
	 */
	private void loadUser(HttpServletRequest request) throws ServletException {
		String username = request.getParameter("username");

		if (username == null)
			throw new ServletException("username not found");

		User user = userManager.retrieve(new String(Base64.getDecoder().decode(username)));

		// TODO make it an error handler
		if (user == null)
			user = new User(1, username, null, null);

		request.getSession().setAttribute("user", user);
	}

	/**
	 * Load user settings using session user Only launched after loading user
	 * 
	 * @param request
	 */
	private void loadSettings(HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		Settings settings = settingManager.retrieveSingle(user);

		request.getSession().setAttribute("settings", settings);
		request.getSession().setAttribute("language", settings.getLanguage());
	}
}
