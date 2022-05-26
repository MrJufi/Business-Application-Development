package controller;

import model.editProfileModel;
import model.manageGameModel;

public class editProfileController {
	
	editProfileModel editProfile;

	public editProfileController() {
		
	}
	
	public boolean update(String username, String email, String phone, String address, String gender, String Id) {
		editProfile = new editProfileModel(username, email, phone, address, gender, Id);
		boolean updated = editProfile.update();
		
		return updated;		
	}
	
	
	
}
