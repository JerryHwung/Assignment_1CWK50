/**
 * This servlet let user login
 * @author Hong Jin Hwung_17004464
 */
package servlets;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.UserDAO;
import security.MD5Salt;

public class ServletLogin extends HttpServlet{
	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	UserDAO dao = new UserDAO();
	MD5Salt hash = new MD5Salt();
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
		String inPword = null;
		String pwordForVerify = null;
		byte[] salt = null;
		// retrieve password and salt based on username
		try {
			pwordForVerify = dao.getPassword(username);
			salt = dao.getSalt(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// hash the password submit by user
		inPword = MD5Salt.hashIt(password,salt);
		// if password matches
		if( inPword.equals(pwordForVerify) ) {
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
			HttpSession session = req.getSession();
			session.setAttribute("notification", "Invalid username or password, please try again.");
			view.forward(req, resp);
		}
	}
}
