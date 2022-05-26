package controller;

import java.util.Vector;

import Customer.BuyGame;
import model.manageGameModel;

public class manageGameController {

	private manageGameModel manageGame;

	
	public manageGameController() {		
		manageGame = new manageGameModel();
		
	}
	
	public boolean insert(String id, String name, String type, int price, int stock) {		
		manageGame = new manageGameModel(id , name, type, price, stock);
		boolean inserted = manageGame.insert();
		
		return inserted;		
	}
	
	public boolean update(String id, String name, String type, int price) {
		manageGame = new manageGameModel(id, name, type, price);
		boolean updated = manageGame.update();
		

		return updated;		
	}
	
	public boolean addStock(String id, int stock) {
		manageGame = new manageGameModel(id, stock);
		boolean updated = manageGame.addStock();
		

		return updated;		
	}
	
	public boolean delete(String id) {
		manageGame = new manageGameModel();
		manageGame.setGameId(id);
		boolean deleted = manageGame.delete();
		

		return deleted;	
	}	
}
