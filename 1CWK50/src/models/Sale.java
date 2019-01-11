/**
 * This is the sale constructor
 * @author Hong Jin Hwung_17004464
 */
package models;

public class Sale {
	private int vehicle_id;
	private String sold_date;
	private int sold_price;
	private String status;
	
	public Sale(int vehicle_id, String sold_date, int sold_price, String status) {
		this.vehicle_id = vehicle_id;
		this.sold_date = sold_date;
		this.sold_price = sold_price;
		this.status = status;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getSold_date() {
		return sold_date;
	}
	public void setSold_date(String sold_date) {
		this.sold_date = sold_date;
	}
	public int getSold_price() {
		return sold_price;
	}
	public void setSold_price(int sold_price) {
		this.sold_price = sold_price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString() {
		String s = "Vehicle ID = "+this.vehicle_id+'\n'+
				   "Sold Date = "+this.sold_date+'\n'+
				   "Sold Price = "+this.sold_price+'\n'+
				   "Status = "+this.status;
		return s;
	}
	
}
