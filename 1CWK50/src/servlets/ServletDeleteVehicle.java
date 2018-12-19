package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VehicleDAO;

public class ServletDeleteVehicle extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		VehicleDAO dao = new VehicleDAO();
		int vID;
		
		vID = Integer.parseInt(req.getParameter("id"));
		
		try {
			dao.deleteVehicle(vID);
			req.getSession().setAttribute("notification", "Record deleted");
			resp.sendRedirect("./home");
		} catch (SQLException e) {
			e.printStackTrace();
			req.getSession().setAttribute("notification", "Delete operation failed");
			resp.sendRedirect("./home");
		}
	}

}
