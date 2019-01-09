/**
 * This servlet add a new vehicle details
 * @author Hong Jin Hwung_17004464
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VehicleDAO;
import models.Vehicle;

public class ServletAddVehicle extends HttpServlet{
	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	@Override
	// get request
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		// set up the destination for the request
		RequestDispatcher view = req.getRequestDispatcher("jsp/add-vehicle.jsp");
		req.setAttribute("message", "Please insert details");
		// forward request
		view.forward(req, resp);
	}
	@Override
	// post request
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		VehicleDAO dao = new VehicleDAO();
		// declare variables
		String vMake, vModel, vLicenseNumber, vColour, vTransmission, vFuelType, vBodyStyle, vCondition, vNotes;
		int vID, vYear, vPrice, vDoors, vMileage, vEngineSize;
		// extract values from the submitted form
		vID = Integer.parseInt(req.getParameter("id"));
		vMake = (String) req.getParameter("make");
		vModel = (String) req.getParameter("model");
		vYear = Integer.parseInt(req.getParameter("year"));
		vPrice = Integer.parseInt(req.getParameter("price"));
		vLicenseNumber = (String) req.getParameter("license number");
		vColour = (String) req.getParameter("colour");
		vDoors = Integer.parseInt(req.getParameter("doors"));
		vTransmission = (String) req.getParameter("transmission");
		vMileage = Integer.parseInt(req.getParameter("mileage"));
		vFuelType = (String) req.getParameter("fuel type");
		vEngineSize = Integer.parseInt(req.getParameter("engine size"));
		vBodyStyle = (String) req.getParameter("body style");
		vCondition = (String) req.getParameter("condition");
		vNotes = (String) req.getParameter("notes");
		Vehicle newV = new Vehicle(vID, vMake, vModel, vYear,  vPrice, vLicenseNumber, vColour, vDoors, vTransmission, vMileage, vFuelType, vEngineSize, vBodyStyle, vCondition, vNotes);
		
		try {
			// execute method to insert a new vehicle
			dao.insertVehicle(newV);
			RequestDispatcher view = req.getRequestDispatcher("jsp/add-vehicle.jsp");
			req.setAttribute("message", "Record successfully created");
			view.forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher view = req.getRequestDispatcher("jsp/add-vehicle.jsp");
			req.setAttribute("message", "Insert failed");
			view.forward(req, resp);
		}
	}
}