/**
 * This is the web front-end controller
 * @author Hong Jin Hwung_17004464
 */
package controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;


public class Controller {

	// Server controller
	public static void main(String[] args) throws Exception {
		// create a server on port 8080
		Server server = new Server(8080);
		//create the WebAppContext for the created content
		WebAppContext ctx = new WebAppContext();
		// where web contents stored
		ctx.setResourceBase("webapp");
		// base url(landing page)
		ctx.setContextPath("/index");
		// execute configuration
		Configure(server);
		// Servlets
		ctx.addServlet("servlets.ServletHome", "/home");
		ctx.addServlet("servlets.ServletLogin", "/login");
		ctx.addServlet("servlets.ServletLogout", "/logout");
		ctx.addServlet("servlets.ServletAddVehicle", "/addVehicle");
		ctx.addServlet("servlets.ServletUpdateVehicle", "/update"); 
		ctx.addServlet("servlets.ServletDeleteVehicle", "/delete");
		server.setHandler(ctx);
		// start the server
		server.start();
		server.join();
	}
	// method for configuration
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
}
