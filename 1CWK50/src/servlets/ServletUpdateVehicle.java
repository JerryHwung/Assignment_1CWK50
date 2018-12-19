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

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		VehicleDAO dao = new VehicleDAO();
		int vID;
		vID = Integer.parseInt(req.getParameter("id"));
		try {
			Vehicle tempV = dao.getVehicle(vID);
			RequestDispatcher view = req.getRequestDispatcher("jsp/update-vehicle.jsp");
			req.setAttribute("message", "This page link to ServletUpdateVehicle");
			req.setAttribute("v",tempV);
			view.forward(req, resp);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		VehicleDAO dao = new VehicleDAO();
		String vMake, vModel, vLicenseNumber, vColour, vTransmission, vFuelType, vBodyStyle, vCondition, vNotes;
		int vID, vYear, vPrice, vDoors, vMileage, vEngineSize;
		
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
			dao.updateVehicle(newV,vID);
			req.getSession().setAttribute("notification", "Record updated successfully");
			resp.sendRedirect("./home");
		} catch (SQLException e) {
			e.printStackTrace();
			req.getSession().setAttribute("notification", "Record update failed");
			resp.sendRedirect("./home");
		}
	}
}
