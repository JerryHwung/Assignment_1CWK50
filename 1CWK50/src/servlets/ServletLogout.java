/**
 * This servlet logs out user
 * @author Hong Jin Hwung_17004464
 */
package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogout extends HttpServlet{
	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	
	@Override
	// do a post request
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{ 
		HttpSession session = req.getSession();
		// create a notification to tell user log out successfully
		session.setAttribute("notification", "You have been successfully logged out!");
		// remove the user logged in session
		session.removeAttribute("user");
		// redirect to login page
		resp.sendRedirect("./login");
		
	}

}
