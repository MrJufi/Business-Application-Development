package model;

import java.sql.ResultSet;
import java.util.Vector;

import connect.Connect;

public class userModel {
	
	
	private Connect con = new Connect();
	private String table = "users";
	private String id;
	private String username;
	private String email;
	private String gender;
	private String address;
	private String phone;
	private String role;
	private String password;
	private String date;
	

	public userModel(String id, String username, String email, String password, String date, String gender,
			String address, String phone, String role) {
		
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.role = role;
		this.password = password;
		this.date = date;
	}
	public userModel(String id) {		
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public userModel() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
/*	
	public Vector<userModel> getAll(){
		String query = "SELECT * FROM " + this.table;
		ResultSet rs = con.getData(query);		
		Vector<userModel> userList = new Vector<userModel>();			
		 try {
			while(rs.next()) {
			String id = rs.getString("UserID"),
				username = rs.getString("UserName"),
				email = rs.getString("UserEmail"),
				gender = rs.getString("UserGender"),
				address = rs.getString("UserAddress"),
				phone = rs.getString("UserPhone"),
				role = rs.getString("UserRole");			
			userList.add(new userModel(id, username, email, gender, address, phone, role));		
			
			 }
			return userList;			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return null;		
	}
	*/

}
