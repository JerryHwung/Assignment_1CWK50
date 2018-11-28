package controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;


public class Controller {

	//Server controller
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);//creating the server on port 8080
		WebAppContext ctx = new WebAppContext();//creating the WebAppContext for the created content
		ctx.setResourceBase("webapp");//where web contents stored
		ctx.setContextPath("/home");//base url(landing page)
		Configure(server);
		ctx.addServlet("servlets.ServletHome", "/home");
		ctx.addServlet("servlets.ServletLogin", "/login");
		server.setHandler(ctx);
		server.start();
		server.join();
	}
	private static void Configure(Server server)
	{
		org.eclipse.jetty.webapp.Configuration.ClassList classlist = 
		org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);
		
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
				"org.eclipse.jetty.plus.webapp.EnvConfiguration",
				"org.eclipse.jetty.plus.webapp.PlusConfiguration");
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");
		
	}
	
	//Show all data in a table
	/*
	public static void main (String[] args) throws Exception {
		Server server = new Server(8005);//listen on port 8005
		ServletHandler handler = new ServletHandler();
		server.setHandler(handler);
		handler.addServletWithMapping(TestServlet.class,"/*");
		server.start();
		server.join();
	}
	@SuppressWarnings("serail")
	public static class TestServlet extends HttpServlet {
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
		{
			response.setContentType("text/html");
			response.setStatus(HttpServletResponse.SC_OK);
			final String head = 
				"<html><head></head><body><table>"+
				"<tr><th>#</th><th>Make</th><th>Model</th><th>Year</th>"+
				"<th>Price</th><th>License Number</th><th>Colour</th><th>Number of Doors</th>"+
				"<th>Transmission</th><th>Mileage</th><th>Fuel Type</th><th>Engine Size</th>"+
				"<th>Body Style</th><th>Condition</th><th>Notes</th>"+
				"</tr>";
			final String foot = "</table></body></html>";
			
			//insert getAllVehicle() to retrieve data
			VehicleDAO dao = new VehicleDAO();
			ArrayList<Vehicle> allVehicles = null;
			try {
				allVehicles = dao.getAllVehicle();
				PrintWriter out = response.getWriter();
				out.println(head);
				for(Vehicle v : allVehicles)
				{
					out.println(
							"<tr><td>"+v.getVehicle_id()+
							"</td><td>"+v.getMake()+
							"</td><td>"+v.getModel()+
							"</td><td>"+v.getYear()+
							"</td><td>"+v.getPrice()+
							"</td><td>"+v.getLicense_number()+
							"</td><td>"+v.getColour()+
							"</td><td>"+v.getNumber_doors()+
							"</td><td>"+v.getTransmission()+
							"</td><td>"+v.getMileage()+
							"</td><td>"+v.getFuel_type()+
							"</td><td>"+v.getEngine_size()+
							"</td><td>"+v.getBody_style()+
							"</td><td>"+v.getCondition()+
							"</td><td>"+v.getNotes());
				}
				out.println(foot);
			} catch (SQLException e) {
				PrintWriter out = response.getWriter();
				out.println("Failed");}
		}
	}*/
	/*
	//console controller
	public static void main(String[] args) throws SQLException {
		
		VehicleDAO dao = new VehicleDAO();
		int choice;
		//Completed method
		
		//Retrieve all vehicles
		/*ArrayList<Vehicle> allVehicles = null;
		allVehicles = dao.getAllVehicle();
		for (Vehicle v : allVehicles)
		{
			System.out.println("---------------------");
			System.out.println(v);
		}
		
		//Retrieve a specific vehicle
		int tempGetID = 9;
		Vehicle tempV = dao.getVehicle(tempGetID);
		System.out.println("---------------------");
		System.out.println("Details of selected vehicle: ");
		System.out.println(tempV);
		
		//Delete a specific vehicle
		int tempDelID = 9;
		Boolean deleted = dao.deleteVehicle(tempDelID);
		System.out.println("---------------------");
		if (deleted = true){
			System.out.println("Delete operation successfully done");
		}
		else System.out.println("Delete operation failed");
		
		//Insert a new vehicle
		Vehicle newV = new Vehicle(4, "Subaru", "XV", 2018,  25000, "IG18DRD", "Silver", 5, "Manual", 4474, "Petrol", 1600, "SUV", "Good", "Reversing Camera");
		Boolean insert = dao.insertVehicle(newV);
		System.out.println("---------------------");
		if (insert = true){
			System.out.println("Records successfully created");
		} else System.out.println("Insert failed");
		
		//Update a vehicle
		Vehicle updateV = new Vehicle(4, "Subaru", "XV", 2018,  26000, "IG18DRD", "Silver", 5, "Manual", 4474, "Petrol", 1600, "SUV", "Good", "Reversing Camera");
		int tempUpID = 4;
		Boolean updated = dao.updateVehicle(updateV, tempUpID);
		System.out.println("---------------------");
		if (updated = true){
			System.out.println("Records successfully updated");
		} else System.out.println("Update failed");
		
		//Testing site (Console)
		Scanner input = new Scanner(System.in);
		System.out.println("-------------------------");
		System.out.println("Vehicle Inventory System \nChoose from these options");
		System.out.println("-------------------------");
		System.out.println("1 - Retrieve all vehicles");
		System.out.println("2 - Search for vehicle");
		System.out.println("3 - Insert new vehicle into database");
		System.out.println("4 - Update existing vehicle details");
		System.out.println("5 - Delete vehicle from database");
		System.out.println("6 - Exit");
		System.out.println("Enter choice > ");
		choice = input.nextInt();
		
		switch(choice){
		case 1: 
			ArrayList<Vehicle> allVehicles = null;
			allVehicles = dao.getAllVehicle();
			for (Vehicle v : allVehicles)
			{
				System.out.println("---------------------");
				System.out.println(v);
			}
			break;
		
		case 2:
			System.out.println("Please enter vehicle ID > ");
			int tempGetID;
			tempGetID = input.nextInt();
			Vehicle tempV = dao.getVehicle(tempGetID);
			System.out.println("---------------------");
			System.out.println("Details of selected vehicle: ");
			System.out.println(tempV);
			break;
		
		case 3:
			System.out.println("Please enter new vehicle details..");
			String vMake, vModel, vLicenseNumber, vColour, vTransmission, vFuelType, vBodyStyle, vCondition, vNotes;
			int vID, vYear, vPrice, vDoors, vMileage, vEngineSize;
			System.out.println("Enter vehicle ID: ");
			vID = input.nextInt();
			//Placing an input.nextLine(); after each .nextInt() is required if the next input is nextLine();
			//without placing a nextLine(); will make it ignores the next .nextLine()
			//Reference: https://stackoverflow.com/questions/5032356/using-scanner-nextline
			input.nextLine();
			System.out.println("Enter vehicle make: ");
			vMake = input.nextLine();
			System.out.println("Enter vehicle model: ");
			vModel = input.nextLine();
			System.out.println("Enter vehicle year: ");
			vYear = input.nextInt();
			System.out.println("Enter vehicle price: ");
			vPrice = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle license number: ");
			vLicenseNumber = input.nextLine();
			System.out.println("Enter vehicle colour: ");
			vColour = input.nextLine();
			System.out.println("Enter vehicle number of doors: ");
			vDoors = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle transmission type (manual/automatic): ");
			vTransmission = input.nextLine();
			System.out.println("Enter vehicle mileage: ");
			vMileage = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle fuel type (petrol, diesel, hybrid, electric): ");
			vFuelType = input.nextLine();
			System.out.println("Enter vehicle engine size (cc): ");
			vEngineSize = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle body style (hatchback, estate, SUV, MVP coupe): ");
			vBodyStyle = input.nextLine();
			System.out.println("Enter vehicle condition(e.g. like new, good, fair): ");
			vCondition = input.nextLine();
			System.out.println("Enter vehicle notes (special features such as sat nav): ");
			vNotes = input.nextLine();
			System.out.println("Creating new record...");
			Vehicle newV = new Vehicle(vID, vMake, vModel, vYear,  vPrice, vLicenseNumber, vColour, vDoors, vTransmission, vMileage, vFuelType, vEngineSize, vBodyStyle, vCondition, vNotes);
			Boolean insert = dao.insertVehicle(newV);
			System.out.println("---------------------");
			if (insert = true){
				System.out.println("Records successfully created");
			} else System.out.println("Insert failed");
			break;
		
		case 4:
			System.out.println("Please enter vehicle ID > ");
			int tempUpID;
			tempUpID = input.nextInt();
			System.out.println("Please enter new vehicle details..");
			String upMake, upModel, upLicenseNumber, upColour, upTransmission, upFuelType, upBodyStyle, upCondition, upNotes;
			int upID, upYear, upPrice, upDoors, upMileage, upEngineSize;
			System.out.println("Enter vehicle ID: ");
			upID = input.nextInt();
			//Placing an input.nextLine(); after each .nextInt() is required if the next input is nextLine();
			//without placing a nextLine(); will make it ignores the next .nextLine()
			//Reference: https://stackoverflow.com/questions/5032356/using-scanner-nextline
			input.nextLine();
			System.out.println("Enter vehicle make: ");
			upMake = input.nextLine();
			System.out.println("Enter vehicle model: ");
			upModel = input.nextLine();
			System.out.println("Enter vehicle year: ");
			upYear = input.nextInt();
			System.out.println("Enter vehicle price: ");
			upPrice = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle license number: ");
			upLicenseNumber = input.nextLine();
			System.out.println("Enter vehicle colour: ");
			upColour = input.nextLine();
			System.out.println("Enter vehicle number of doors: ");
			upDoors = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle transmission type (manual/automatic): ");
			upTransmission = input.nextLine();
			System.out.println("Enter vehicle mileage: ");
			upMileage = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle fuel type (petrol, diesel, hybrid, electric): ");
			upFuelType = input.nextLine();
			System.out.println("Enter vehicle engine size (cc): ");
			upEngineSize = input.nextInt();
			input.nextLine();
			System.out.println("Enter vehicle body style (hatchback, estate, SUV, MVP coupe): ");
			upBodyStyle = input.nextLine();
			System.out.println("Enter vehicle condition(e.g. like new, good, fair): ");
			upCondition = input.nextLine();
			System.out.println("Enter vehicle notes (special features such as sat nav): ");
			upNotes = input.nextLine();
			System.out.println("Updating record...");
			Vehicle updateV = new Vehicle(upID, upMake, upModel, upYear,  upPrice, upLicenseNumber, upColour, upDoors, upTransmission, upMileage, upFuelType, upEngineSize, upBodyStyle, upCondition, upNotes);
			Boolean updated = dao.updateVehicle(updateV, tempUpID);
			System.out.println("---------------------");
			if (updated = true){
				System.out.println("Records successfully updated");
			} else System.out.println("Update failed");
			break;
			
		case 5:
			int tempDelID;
			System.out.println("Please enter vehicle ID > ");
			tempDelID = input.nextInt();
			Boolean deleted = dao.deleteVehicle(tempDelID);
			System.out.println("---------------------");
			if (deleted = true){
				System.out.println("Delete operation successfully done");
			}
			else System.out.println("Delete operation failed");
			break;
			
		case 6:
			System.out.println("---------------------");
			System.out.println("Thank you for using this system, see you!");
			break;
			
		default:
			System.out.println("Please enter number between 1 - 6");
			
			break;
		}
	}
*/

}
