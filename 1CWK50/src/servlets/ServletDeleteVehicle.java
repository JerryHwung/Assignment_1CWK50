/**
 * This servlet deletes a vehicle details
 * @author Hong Jin Hwung_17004464
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.VehicleDAO;

public class ServletDeleteVehicle extends HttpServlet{
	// set up a universal version identifier
	private static final long serialVersionUID = 1L;
	
	@Override
	// post request
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//declare variables
		VehicleDAO dao = new VehicleDAO();
		int vID;
		// convert string to integer
		vID = Integer.parseInt(req.getParameter("id"));
		
		try {
			//  execute method for deleting a vehicle
			dao.deleteVehicle(vID);
			req.getSession().setAttribute("notification", "Record deleted");
			// redirect to home page
			resp.sendRedirect("./home");
		} catch (SQLException e) {
			e.printStackTrace();
			req.getSession().setAttribute("notification", "Delete operation failed");
			resp.sendRedirect("./home");
		}
	}

}
