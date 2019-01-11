/**
 * This is the user constructor
 * @author Hong Jin Hwung_17004464
 */
package models;

public class User {
	private String firstname;
	private String surname;
	private String username;
	private String password;
	private int user_type;
	private byte[] salt;
	
	public User(String firstname, String surname, String username, String password, int user_type, byte[] salt) {
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.user_type = user_type;
		this.salt = salt;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	public String toString() {
		String s = "Firstname = "+this.firstname+'\n'+
				   "Surname = "+this.surname+'\n'+
				   "Username = "+this.username+'\n'+
				   "Password = "+this.password+'\n'+
				   "User Type = "+this.user_type+'\n'+
				   "salt = "+this.salt;
		return s;
	}
	
}
