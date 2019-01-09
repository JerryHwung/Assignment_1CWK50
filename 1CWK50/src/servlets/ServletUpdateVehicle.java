/**
 * This servlet updates a vehicle details
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

public class ServletUpdateVehicle extends HttpServlet {

	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	@Override
	// get request
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		VehicleDAO dao = new VehicleDAO();
		int vID;
		// convert string into integer
		vID = Integer.parseInt(req.getParameter("id"));
		try {
			// execute the method for retrieving a vehicle details
			Vehicle tempV = dao.getVehicle(vID);
			// set the destination for this request
			RequestDispatcher view = req.getRequestDispatcher("jsp/update-vehicle.jsp");
			req.setAttribute("message", "Update Vehicle");
			req.setAttribute("v",tempV);
			// forward the request
			view.forward(req, resp);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	@Override
	// post request
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		VehicleDAO dao = new VehicleDAO();
		// declare variables
		String vMake, vModel, vLicenseNumber, vColour, vTransmission, vFuelType, vBodyStyle, vCondition, vNotes;
		int vID, vYear, vPrice, vDoors, vMileage, vEngineSize;
		// extract details from the form
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
			// update the vehicle using method from vehicleDAO
			dao.updateVehicle(newV,vID);
			// create a successful notification
			req.getSession().setAttribute("notification", "Record updated successfully");
			// redirect to home page
			resp.sendRedirect("./home");
		} catch (SQLException e) {
			e.printStackTrace();
			// create a fail notification
			req.getSession().setAttribute("notification", "Record update failed");
			// redirect to home page
			resp.sendRedirect("./home");
		}
	}
}
