/**
 * This is the console controller for testing VehicleDAO CRUD methods
 * @author Hong Jin Hwung_17004464
 */
package controller;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Vehicle;
import security.MD5Salt;

public class ConsoleController {

	public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, NoSuchProviderException {
		/**
		 * Menu displayed for user to choose which CRUD method to execute
		 * The menu will print out again once the chosen method completed
		 * By selecting exit, the menu will stop the loop
		 */
		VehicleDAO dao = new VehicleDAO();
		UserDAO dao2 = new UserDAO();
		MD5Salt hash = new MD5Salt();
		/**
		 * @param choice = the option that user chose
		 */
		int choice=0;
		while(choice!=7) {
		/**
		 * @param input = a scanner
		 */
		Scanner input = new Scanner(System.in);
		//Console menu
		System.out.println("-------------------------");
		System.out.println("Vehicle Inventory System \nChoose from these options");
		System.out.println("-------------------------");
		System.out.println("1 - Retrieve all vehicles");
		System.out.println("2 - Search for vehicle");
		System.out.println("3 - Insert new vehicle into database");
		System.out.println("4 - Update existing vehicle details");
		System.out.println("5 - Delete vehicle from database");
		System.out.println("6 - Hash password and update into the database");
		System.out.println("7 - Exit");
		System.out.println("Enter choice > ");
		choice = input.nextInt();
		switch(choice){
		// get all vehicles' details
		case 1: 
			ArrayList<Vehicle> allVehicles = null;
			allVehicles = dao.getAllVehicle();
			for (Vehicle v : allVehicles)
			{
				System.out.println("---------------------");
				System.out.println(v);
			}
			break;
		// use a Vehicle ID to retrieve a specific vehicle's details
		case 2:
			System.out.println("Please enter vehicle ID > ");
			/**
			 * @param tempGetID = a temporary place to store vehicle ID for this case
			 */
			int tempGetID;
			tempGetID = input.nextInt();
			// execute method to retrieve the vehicle's details
			Vehicle tempV = dao.getVehicle(tempGetID);
			System.out.println("---------------------");
			System.out.println("Details of selected vehicle: ");
			System.out.println(tempV);
			break;
		// insert a new vehicle
		case 3:
			System.out.println("Please enter new vehicle details..");
			/**
			 * @param vMake = Vehicle's make
			 * @param vModel = Vehicle's model
			 * @param vLicenseNumber = Vehicle's License Number
			 * @param vColour = Vehicle's colour
			 * @param vTransmission = Vehicle's transmission
			 * @param vFuelType = Vehicle's fuel type
			 * @param vBodyStyle = Vehicle's Body Style
			 * @param vCondition = Vehicle's condition
			 * @param vNotes = Additional notes about the vehicle
			 * @param vID = vehicle's ID 
			 * @param vYear = vehicle's age
			 * @param vPrice = vehicle's price
			 * @param vDoors = vehicle's number of doors
			 * @param vMileage = Vehicle's milegawe
			 * @param vEngineStyle = Vehicles engine style 
			 */
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
		// Update a vehicle's details
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
			/**
			 * @param updateV = an updated vehicle object
			 */
			Vehicle updateV = new Vehicle(upID, upMake, upModel, upYear,  upPrice, upLicenseNumber, upColour, upDoors, upTransmission, upMileage, upFuelType, upEngineSize, upBodyStyle, upCondition, upNotes);
			Boolean updated = dao.updateVehicle(updateV, tempUpID);
			System.out.println("---------------------");
			if (updated = true){
				System.out.println("Records successfully updated");
			} else System.out.println("Update failed");
			break;
			
		// Delete a chosen vehicle based on vehicle ID
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
		// Hash and update password
		case 6:
			// declare variables
			String tempUname, tempPword;
			byte[] tempSalt;
			// ask user to enter username
			System.out.println("Please enter username > ");
			input.nextLine();
			tempUname = input.nextLine();
			// Generate salt
			tempSalt = MD5Salt.getSalt();
			// Retrieve password from Database
			tempPword = dao2.getPassword(tempUname);
			System.out.println("insecure pword "+tempPword);
			// hash password
			String hashedPword = MD5Salt.hashIt(tempPword,tempSalt);
			System.out.println("secure pword "+hashedPword);
			// update salt and password
			Boolean updatedP = dao2.updatePassword(hashedPword, tempSalt, tempUname);
			System.out.println("---------------------");
			if (updatedP = true){
				System.out.println("Update operation successfully done");
			}
			else System.out.println("Update operation failed");
			break;
		// Exit
		case 7:
			// print out exit message
			System.out.println("---------------------");
			System.out.println("Thank you for using this system, see you!");
			break;
		// When user chose a number not in between 1 to 6	
		default:
			System.out.println("Please enter number between 1 - 6");
			
			break;
		}}
	}

}
