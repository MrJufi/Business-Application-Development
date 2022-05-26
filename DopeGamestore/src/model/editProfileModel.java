package model;

import connect.Connect;

public class editProfileModel {

	private String Id;
	private String username;
	private String email;
	private String phone;
	private String address;
	private String gender;
	private Connect con = new Connect();
	
	public editProfileModel() {
		
	}

	public editProfileModel(String username, String email, String phone, String address, String gender, String Id) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.Id = Id;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public boolean update() {
		String query = String.format("UPDATE users SET UserName = '%s' "
				+ ", UserEmail = '%s' , UserPhone = '%s' , UserAddress = '%s' "
				+ ", UserGender = '%s' WHERE UserID = '%s'", username, email, phone, address, gender, Id);		
		if(con.updateData(query) != 0) {
			return true;
		}		
		return false;	
	}
	
	

}
