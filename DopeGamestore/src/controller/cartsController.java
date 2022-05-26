package controller;

import model.cartsModel;
import model.manageGameModel;

public class cartsController {

	private cartsModel cartsGame;

	
	public cartsController() {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean insert(String id, String name, int quantity) {		
		cartsGame = new cartsModel(id, name, quantity);
		boolean inserted = cartsGame.insert();
		
		return inserted;		
	}
	
	public boolean delete(String id) {		
		cartsGame = new cartsModel(id);
		boolean inserted = cartsGame.delete(); 
		
		return inserted;		
	}
	
	public boolean clearCart() {		
		cartsGame = new cartsModel();
		boolean inserted = cartsGame.clearCart(); 
		
		return inserted;		
	}
	
	

}
