/**
 * This servlet let user login
 * @author Hong Jin Hwung_17004464
 */
package servlets;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet{
	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	
	@Override
	// get request
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		// set forward destination
		RequestDispatcher view = req.getRequestDispatcher("jsp/login.jsp");
		// set up a message about 
		req.setAttribute("message", "Welcome to the login page");
		// forward request
		view.forward(req, resp);
	}
	@Override
	// post request
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// declare variables
		String username = (String) req.getParameter("username");
		String password = (String) req.getParameter("password");
		// if admin logged in
		if(username.equals("admin") && password.equals("admin")) {
			HttpSession session = req.getSession();
			//set up a session
			session.setAttribute("user","admin");
			//set the session to expire in 30 mins
			session.setMaxInactiveInterval(30*60);
			//set up a cookie
			Cookie uname = new Cookie("user",username);
			resp.addCookie(uname);
			//added a encoded URL string because user who disabled cookies cannot receive the JSESSIONID cookie from client
			String encodedURL = resp.encodeRedirectURL("./home");
			resp.sendRedirect(encodedURL);
		}
		else {
			// user will get redirect to login page to try again
			RequestDispatcher view = req.getRequestDispatcher("jsp/login.jsp");
			req.setAttribute("failLogin", "Please try again.");
			view.forward(req, resp);
		}
	}
}
