import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VehicleDAO {

	private Connection getDBConnection(){
		Connection conn = null;
		
		try{
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try{
			String url = "jdbc:sqlite:vehicles.sqlite";
			conn = DriverManager.getConnection(url);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public ArrayList<Vehicle> getAllVehicle() throws SQLException{
		System.out.println("Retrieving all vehicles...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM vehicles;";
		Vehicle temp = null;
		ArrayList<Vehicle> vehicleList = new ArrayList<>();
		
		try{
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = "+query);
			result = statement.executeQuery(query);
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
					
					vehicleList.add(new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes));
				}
		} finally{
			if (result != null){result.close();}
			if (statement != null){statement.close();}
			if (dbConnection != null){dbConnection.close();}
		}
		return vehicleList;
	}
	//work in progress...
	/*public Vehicle getVehicle(int newV){
		
	}
	public Boolean deleteVehicle(int oldV){
		
	}
	public Boolean insertVehicle(Vehicle v){
		
	}
	public Boolean updateVehicle(Vehicle ve, int i){
		
	}*/
}
