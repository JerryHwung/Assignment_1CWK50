import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

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
		*/
		//Testing site
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
		default:
			System.out.println("Please enter number between 1 - 6");
			
			break;
		}
	}

}
