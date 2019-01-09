/**
 * This is the vehicle DAO which contains the CRUD method
 * @author Hong Jin Hwung_17004464
 */
package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Vehicle;

public class VehicleDAO {
	// Function for connecting database
	private Connection getDBConnection(){
		Connection conn = null;
		
		try{
			// name of the database
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try{
			// type of the database file
			String url = "jdbc:sqlite:vehicles.sqlite";
			conn = DriverManager.getConnection(url);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return conn;
	}
	// Method to retrieve all vehicle details
	public ArrayList<Vehicle> getAllVehicle() throws SQLException{
		System.out.println("Retrieving all vehicles...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		// query to retrieve all details from vehicles
		String query = "SELECT * FROM vehicles;";
		Vehicle temp = null;
		// create an array list to store data
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		
		try{
			// Connect to database
			dbConnection = getDBConnection();
			// create a statement
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = "+query);
			// execute query and store the return value in result
			result = statement.executeQuery(query);
				// loop through every row
				while(result.next()){
					int vehicle_id = result.getInt("vehicle_id");
					String make = result.getString("make");
					String model = result.getString("model");
					int year = result.getInt("year");
					int price = result.getInt("price");
					String license_number = result.getString("license_number");
					String colour = result.getString("colour");
					int number_doors = result.getInt("number_doors");
					String transmission = result.getString("transmission");
					int mileage = result.getInt("mileage");
					String fuel_type = result.getString("fuel_type");
					int engine_size = result.getInt("engine_size");
					String body_style = result.getString("body_style");
					String condition = result.getString("condition");
					String notes = result.getString("Notes");
					// add vehicle object into the array list
					vehicleList.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes));
				}
		} finally{
			// close the connection
			if (result != null){result.close();}
			if (statement != null){statement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return vehicleList;
	}
	// Method to retrieve a specific vehicle's details based on ID
	// Added prepared statement to prevent SQL injection
	public Vehicle getVehicle(int vehicleID)throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		ResultSet result = null;
		Vehicle temp = null;
		// query to retrieve the selected vehicle
		String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
		try{
			dbConnection = getDBConnection();
			// create a prepared statement
			preparedStatement = dbConnection.prepareStatement(query);
			// set a variable for the first questionmark
			preparedStatement.setInt(1,vehicleID);
			System.out.println("DBQuery = "+query);
			result = preparedStatement.executeQuery();
			//Just run executeQuery() and not executeQuery(String) when doing a SELECT query with params.
			//Run with executeQuery(String) will gives error
				while(result.next()){
					int vehicle_id = result.getInt("vehicle_id");
					String make = result.getString("make");
					String model = result.getString("model");
					int year = result.getInt("year");
					int price = result.getInt("price");
					String license_number = result.getString("license_number");
					String colour = result.getString("colour");
					int number_doors = result.getInt("number_doors");
					String transmission = result.getString("transmission");
					int mileage = result.getInt("mileage");
					String fuel_type = result.getString("fuel_type");
					int engine_size = result.getInt("engine_size");
					String body_style = result.getString("body_style");
					String condition = result.getString("condition");
					String notes = result.getString("Notes");
					// store the retrieved vehicle object in temp
					temp = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
				}
		} finally{
			// close connection
			if (result != null){result.close();}
			if (preparedStatement != null){preparedStatement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return temp;
	}
	// method to delete a vehicle
	// added prepared statement
	public Boolean deleteVehicle(int vehicle_id) throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		// query to delete a vehicle based on ID
		String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
		try{
			// connect to database
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			// set variable in the questionmark
			preparedStatement.setInt(1,vehicle_id);
			System.out.println("DBQuery = "+query);
			// execute query
			preparedStatement.executeUpdate();
		} catch(Exception e){return false;}
		
		finally{
			// close connection
			if (preparedStatement != null){preparedStatement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return true;
	}
	// method to insert/create a new vehicle
	public Boolean insertVehicle(Vehicle v) throws SQLException{
		// reset variables
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		// query for inserting a new vehicle
		String query = "INSERT INTO vehicles (vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, Notes) "+
						"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			// connect to database
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			// set variables into each questionmark
			preparedStatement.setInt(1,v.getVehicle_id());
			preparedStatement.setString(2,v.getMake());
			preparedStatement.setString(3,v.getModel());
			preparedStatement.setInt(4,v.getYear());
			preparedStatement.setInt(5,v.getPrice());
			preparedStatement.setString(6,v.getLicense_number());
			preparedStatement.setString(7,v.getColour());
			preparedStatement.setInt(8,v.getNumber_doors());
			preparedStatement.setString(9,v.getTransmission());
			preparedStatement.setInt(10,v.getMileage());
			preparedStatement.setString(11,v.getFuel_type());
			preparedStatement.setInt(12,v.getEngine_size());
			preparedStatement.setString(13,v.getBody_style());
			preparedStatement.setString(14,v.getCondition());
			preparedStatement.setString(15,v.getNotes());
			System.out.println("DBQuery = "+query);
			// execute query
			preparedStatement.executeUpdate();
		} catch(Exception e){return false;}
		
		finally{
			// close connection
			if (preparedStatement != null){preparedStatement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return true;
	}
	// method to update vehicle details
	public Boolean updateVehicle(Vehicle v, int vehicle_id) throws SQLException{
		// reset variables
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		// query for updating vehicle details
		String query = "UPDATE vehicles SET vehicle_id=?, make=?, model=?, year=?, price=?, license_number=?, colour=?, number_doors=?,"+
						"transmission=?, mileage=?, fuel_type=?, engine_size=?, body_style=?, condition=?, Notes=? WHERE vehicle_id=?";
		try{
			// connect to database
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			// set variables in each questionmark
			preparedStatement.setInt(1,v.getVehicle_id());
			preparedStatement.setString(2,v.getMake());
			preparedStatement.setString(3,v.getModel());
			preparedStatement.setInt(4,v.getYear());
			preparedStatement.setInt(5,v.getPrice());
			preparedStatement.setString(6,v.getLicense_number());
			preparedStatement.setString(7,v.getColour());
			preparedStatement.setInt(8,v.getNumber_doors());
			preparedStatement.setString(9,v.getTransmission());
			preparedStatement.setInt(10,v.getMileage());
			preparedStatement.setString(11,v.getFuel_type());
			preparedStatement.setInt(12,v.getEngine_size());
			preparedStatement.setString(13,v.getBody_style());
			preparedStatement.setString(14,v.getCondition());
			preparedStatement.setString(15,v.getNotes());
			preparedStatement.setInt(16,vehicle_id);
			System.out.println("DBQuery = "+query);
			// execute query
			preparedStatement.executeUpdate();
		} catch(Exception e){return false;}
		
		finally{
			// close connection
			if (preparedStatement != null){preparedStatement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return true;
	}
}
