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

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{		
		VehicleDAO dao = new VehicleDAO();
		try {
			ArrayList<Vehicle> allVehicles = dao.getAllVehicle();
			RequestDispatcher view = req.getRequestDispatcher("jsp/index.jsp");
			req.setAttribute("message", "This page link to servletHome");
			req.setAttribute("AllVehicles", allVehicles);
			view.forward(req, resp);
		} catch (SQLException e) {}
	}
}
