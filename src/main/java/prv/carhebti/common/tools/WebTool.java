package prv.carhebti.common.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import prv.carhebti.business.entities.User;

public class WebTool {
	
	
	public static void swipeSessionAttributes(HttpServletRequest request, String creationKey, String updateKey, String deleteKey) {
		HttpSession session = request.getSession(false);
		
		if (session == null)
			return ;
		
		if (session.getAttribute(creationKey) != null) {
			request.setAttribute(creationKey, session.getAttribute(creationKey));
			session.removeAttribute(creationKey);
		}
		
		if (session.getAttribute(updateKey) != null) {
			request.setAttribute(updateKey, session.getAttribute(updateKey));
			session.removeAttribute(updateKey);
		}
		
		if (session.getAttribute(deleteKey) != null) {
			request.setAttribute(deleteKey, session.getAttribute(deleteKey));
			session.removeAttribute(deleteKey);
		}
	}
	
	public static User getUser(HttpServletRequest request) {
		Object sessionUser = request.getSession().getAttribute("user");
		return (sessionUser == null)?null:(User)sessionUser;
	}
}
