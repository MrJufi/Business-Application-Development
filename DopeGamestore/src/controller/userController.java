package controller;

import java.sql.ResultSet;
import java.util.Vector;

import connect.Connect;
import model.manageGameModel;
import model.userModel;

public class userController {

	public static userController controller = null;
	private Connect con = Connect.getInstance();
	private userModel userList;
	
	public userController() {
		userList = new userModel();
	}
	
	//public Vector<userModel> getAll(){
		//return userList.getAll();
	//}
	

}
