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

import security.MD5Salt;

public class ServletLogin extends HttpServlet{
	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	// set up a connection to database
	private Connection getDBConnection(){
		Connection conn = null;
		try{
			// name of the database
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try{
			// type of the database file
			String url = "jdbc:sqlite:vehicles.sqlite";
			conn = DriverManager.getConnection(url);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return conn;
	}
	// method for matching username to get password
	public String checkForMatch(String uname, byte[] s ) throws SQLException, NoSuchAlgorithmException, NoSuchProviderException {
		// declare variables
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		ResultSet result = null;
		String pword_db = null;
		// query to retrieve details related to the signed in user
		String query = "SELECT password FROM users WHERE username = ?";
		try{
			// connect to database
			dbConnection = getDBConnection();
			// create a prepared statement
			preparedStatement = dbConnection.prepareStatement(query);
			// set a variable for the questionmark
			preparedStatement.setString(1, uname);
			System.out.println("DBQuery = "+query);
			result = preparedStatement.executeQuery();
			while(result.next()){
				pword_db = result.getString("password");
			}
		} finally{
			// close connection
			if (result != null){result.close();}
			if (preparedStatement != null){preparedStatement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		// hash the password with a method in MD5Salt
		String securePword_db = MD5Salt.hashIt(pword_db, s);
		return securePword_db;
	}
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
		String securePword = null;
		String pwordForVerify = null;
		byte[] salt = null;
		// Generate a salt value for hashing
		try {
			salt = MD5Salt.getSalt();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchProviderException e1) {
			e1.printStackTrace();
		}
		// find a matching username in the database and get the hashed password
			try {
				pwordForVerify = checkForMatch(username, salt);
				securePword = MD5Salt.hashIt(password,salt);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		// if password matches
		if( securePword.equals(pwordForVerify) ) {
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
