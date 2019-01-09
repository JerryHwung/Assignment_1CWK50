/**
 * This servlet is the home page
 * @author Hong Jin Hwung_17004464
 */
package servlets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VehicleDAO;
import models.Vehicle;

public class ServletHome extends HttpServlet{
	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	
	@Override
	// get request
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		VehicleDAO dao = new VehicleDAO();
		try {
			// create an array list to store the result
			ArrayList<Vehicle> allVehicles = dao.getAllVehicle();
			// set up the destination
			RequestDispatcher view = req.getRequestDispatcher("jsp/index.jsp");
			req.setAttribute("message", "Welcome to the home page");
			// pass the results for jsp to use
			req.setAttribute("AllVehicles", allVehicles);
			// forward the request
			view.forward(req, resp);
		} catch (SQLException e) {}
	}
}
