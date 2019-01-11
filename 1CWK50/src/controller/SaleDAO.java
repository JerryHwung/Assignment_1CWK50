package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Sale;
import models.Vehicle;

public class SaleDAO {
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
	// method to show all sales record
	public ArrayList<Sale> getAllSale() throws SQLException{
		System.out.println("Retrieving all sales record...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		// query to retrieve all details from vehicles
		String query = "SELECT * FROM vehicles;";
		Vehicle temp = null;
		// create an array list to store data
		ArrayList<Sale> saleList = new ArrayList<>();
		
		try{
			// Connect to database
			dbConnection = getDBConnection();
			// create a statement
			System.out.println("DBQuery = "+query);
			// execute query and store the return value in result
			result = statement.executeQuery(query);
			// loop through every row
			while(result.next()){
				int vehicle_id = result.getInt("vehicle_id");
				String sold_date = result.getString("sold_date");
				int sold_price = result.getInt("sold_price");
				String status = result.getString("status");
				// add sale objects into array list
				saleList.add(new Sale(vehicle_id, sold_date, sold_price, status));
			}
		} finally {
			// close the connection
			if (result != null){result.close();}
			if (statement != null){statement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return saleList;
	}
	// method to retrieve sales record based on vehicle id
	public Sale getSale(int vehicleID)throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		ResultSet result = null;
		Sale temp = null;
		// query to retrieve the selected vehicle
		String query = "SELECT * FROM sales WHERE vehicle_id = ?";
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
						String sold_date = result.getString("sold_date");
						int sold_price = result.getInt("sold_price");
						String status = result.getString("status");
						// store the retrieved vehicle object in temp
						temp = new Sale(vehicle_id, sold_date, sold_price, status);
						}
				} finally{
					// close connection
					if (result != null){result.close();}
					if (preparedStatement != null){preparedStatement.close();}
					if (dbConnection != null){dbConnection.close();}
				}
			return temp;
	}
	// method to update sales record
	public Boolean updateSale(Sale s, int vehicle_id) throws SQLException{
		// reset variables
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		// query for updating vehicle details
		String query = "UPDATE sales SET vehicle_id=?, sold_date=?, sold_price=?, status=? WHERE vehicle_id=?";
		try{
			// connect to database
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			// set variables in each questionmark
			preparedStatement.setInt(1,s.getVehicle_id());
			preparedStatement.setString(2,s.getSold_date());
			preparedStatement.setInt(3,s.getSold_price());
			preparedStatement.setString(2,s.getStatus());
			preparedStatement.setInt(5,vehicle_id);
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
	// method to delete sales record
	public Boolean deleteSale(int vehicle_id) throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		// query to delete a vehicle based on ID
		String query = "DELETE FROM sales WHERE vehicle_id = ?";
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
	// method to create sales record
	public Boolean insertSale(Sale s) throws SQLException{
		// reset variables
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		// query for inserting a new vehicle
		String query = "INSERT INTO sales (vehicle_id, sold_date, sold_price, status) VALUES (?,?,?,?)";
		try{
			// connect to database
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			// set variables into each questionmark
			preparedStatement.setInt(1,s.getVehicle_id());
			preparedStatement.setString(2,s.getSold_date());
			preparedStatement.setInt(3,s.getSold_price());
			preparedStatement.setString(4,s.getStatus());
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
