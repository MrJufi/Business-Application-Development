package model;

import java.sql.ResultSet;
import java.util.Vector;

import connect.Connect;

public class manageGameModel {

	private Connect con = new Connect();
	private String table = "games";
	private String gameId;
	private String gameName;
	private String gameType;
	private int gamePrice;
	private int gameStock;
	
	public manageGameModel() {
		
	}
	
	public manageGameModel(String gameId, String gameName, String gameType, int gamePrice, int gameStock) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.gameType = gameType;
		this.gamePrice = gamePrice;
		this.gameStock = gameStock;
	}
	
	public manageGameModel(String gameId, String gameName, String gameType, int gamePrice) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.gameType = gameType;
		this.gamePrice = gamePrice;
	}
	
	public manageGameModel(String gameId, int stock) {
		super();
		this.gameId = gameId;	
		gameStock = stock;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public int getGamePrice() {
		return gamePrice;
	}

	public void setGamePrice(int gamePrice) {
		this.gamePrice = gamePrice;
	}

	public int getGameStock() {
		return gameStock;
	}

	public void setGameStock(int gameStock) {
		this.gameStock = gameStock;
	}	
	
	public boolean insert() {			
		String query = String.format("INSERT INTO games VALUES ('%s', '%s', '%s', '%d', '%d')"
				, gameId, gameName, gameType, gamePrice, gameStock);		
		if(con.updateData(query) != 0) {
			return true;
		}	
		return false;	
	}
		
	public boolean update() {
		String query = String.format("UPDATE games SET GameName = '%s' , GameType = '%s' , GamePrice = '%d'"
				+ "WHERE GameID = '%s'", gameName, gameType, gamePrice, gameId);		
		if(con.updateData(query) != 0) {
			return true;
		}		
		return false;	
	}
	
	public boolean addStock() {
		String query = String.format("UPDATE games SET GameStock = '%d'"
				+ "WHERE GameID = '%s'",gameStock, gameId);		
		if(con.updateData(query) != 0) {
			return true;
		}		
		return false;	
	}
	
	public boolean delete() {
		String query = String.format("DELETE FROM games WHERE GameID = '%s'", gameId);
		
		if(con.updateData(query) != 0) {
			return true;
		}
		
		return false;	
	}
	


}
