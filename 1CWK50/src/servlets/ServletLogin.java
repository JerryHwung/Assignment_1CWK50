package servlets;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet{

private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		RequestDispatcher view = req.getRequestDispatcher("jsp/login.jsp");
		req.setAttribute("message", "This page link to SevletLogin");
		view.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String username = (String) req.getParameter("username");
		String password = (String) req.getParameter("password");
		
		if(username.equals("admin") && password.equals("admin")) {
			HttpSession session = req.getSession();
			session.setAttribute("loggedin",true);
			resp.sendRedirect("./home");
		}
		else {
			RequestDispatcher view = req.getRequestDispatcher("jsp/login.jsp");
			req.setAttribute("failLogin", "Please try again.");
			view.forward(req, resp);
		}
	}
}
