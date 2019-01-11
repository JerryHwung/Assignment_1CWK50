package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {
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
	// method to retrieve password based on username
	public String getPassword(String uname)throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		ResultSet result = null;
		String temp = null;
		// query to retrieve password
		String query = "SELECT password FROM users WHERE username = ?";
		try{
			dbConnection = getDBConnection();
			// create a prepared statement
			preparedStatement = dbConnection.prepareStatement(query);
			// set a variable for the first questionmark
			preparedStatement.setString(1,uname);
			System.out.println("DBQuery = "+query);
			result = preparedStatement.executeQuery();
			while (result.next()) {
			temp = result.getString("password");
			}
		} finally{
			// close connection
			if (result != null){result.close();}
			if (preparedStatement != null){preparedStatement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return temp;
	}
	// method to retrieve salt based on username
	public byte[] getSalt(String uname)throws SQLException{
		Connection dbConnection = null;
		PreparedStatement preparedStatement= null;
		ResultSet result = null;
		byte[] temp = null;
		// query to retrieve password
		String query = "SELECT salt FROM users WHERE username = ?";
		try{
			dbConnection = getDBConnection();
			// create a prepared statement
			preparedStatement = dbConnection.prepareStatement(query);
			// set a variable for the first questionmark
			preparedStatement.setString(1,uname);
			System.out.println("DBQuery = "+query);
			result = preparedStatement.executeQuery();
				while(result.next()){
					byte[] salt = result.getBytes("salt");
					temp = salt;
				}
			
		} finally{
			// close connection
			if (result != null){result.close();}
			if (preparedStatement != null){preparedStatement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return temp;
	}
	// method to update password and salt after hashing
	// if method for creating user exist, will immediately upload hashed password and salt to database instead
	public Boolean updatePassword( String securePword, byte[] salt, String uname) throws SQLException{
		// reset variables
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		// query for updating user's password
		String query = "UPDATE users SET password = ?, salt = ? WHERE username = ?";
		try{
			// connect to database
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(query);
			// set variables in each questionmark
			preparedStatement.setString(1, securePword);
			preparedStatement.setBytes(2, salt);
			preparedStatement.setString(3, uname);
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
