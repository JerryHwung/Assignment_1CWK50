package controller;

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
		ctx.setContextPath("/index");//base url(landing page)
		Configure(server);
		ctx.addServlet("servlets.ServletHome", "/home");
		ctx.addServlet("servlets.ServletLogin", "/login");
		ctx.addServlet("servlets.ServletLogout", "/logout");
		ctx.addServlet("servlets.ServletAddVehicle", "/addVehicle");
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
		}*/

}
