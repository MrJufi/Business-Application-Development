package model;

import connect.Connect;

public class cartsModel {

	private Connect con = new Connect();
	
	private String userId;
	private String gameId;
	private int quantity;
	
	
	public cartsModel(String userId, String gameId, int quantity) {
		super();
		this.userId = userId;
		this.gameId = gameId;
		this.quantity = quantity;
	}
	
	public cartsModel(String userId) {
		super();
		this.userId = userId;
	}


	public cartsModel() {
		
	}
		
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getGameId() {
		return gameId;
	}


	public void setGameId(String gameId) {
		this.gameId = gameId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	public boolean insert() {			
		String query = String.format("INSERT INTO carts VALUES ('%s', '%s', '%d')"
				, userId, gameId, quantity);		
		if(con.updateData(query) != 0) {
			return true;
		}	
		return false;	
	}	
	
	
	public boolean delete() {
		String query = String.format("DELETE FROM carts WHERE GameID = '%s'", gameId);		
		if(con.updateData(query) != 0) {
			return true;
		}
		
		return false;	
	}
	
	public boolean clearCart() {
		String query = String.format("DELETE FROM carts");		
		if(con.updateData(query) != 0) {
			return true;
		}
		
		return false;	
	}

}
