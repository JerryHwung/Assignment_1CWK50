import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

	public static void main(String[] args) throws SQLException {

		VehicleDAO dao = new VehicleDAO();
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
		int tempGetID = 9
		Vehicle tempV = dao.getVehicle(tempGetID);
		System.out.println("---------------------");
		System.out.println("Details of selected vehicle: ");
		System.out.println(tempV);
		//Delete a specific vehicle
		int tempDelID = 9
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
		*/
		//Testing site
	}

}
