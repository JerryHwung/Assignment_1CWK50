import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

	public static void main(String[] args) throws SQLException {
		
		VehicleDAO dao = new VehicleDAO();
		
		ArrayList<Vehicle> allVehicles = null;
		allVehicles = dao.getAllVehicle();
		for (Vehicle v : allVehicles)
		{
			System.out.println("---------------------");
			System.out.println(v);
		}
	}

}
