package prv.carhebti.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prv.carhebti.business.dao.TypeManager;
import prv.carhebti.business.entities.Type;

/**
 * Servlet implementation class AddTypeController
 */
@WebServlet("/addType.action")
public class AddTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTypeController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String provider = request.getParameter("provider");
		Boolean odometer = Boolean.valueOf(request.getParameter("odometer"));
		Boolean quantity = Boolean.valueOf(request.getParameter("quantity"));
		
		Type type = new Type(name, provider, odometer, quantity);
		TypeManager manager = new TypeManager();
		manager.addType(type);
		
		RequestDispatcher rd = request.getRequestDispatcher("listTypes.go");
		request.setAttribute("newTypejth", type.getId());
		rd.forward(request, response);
	}

}
