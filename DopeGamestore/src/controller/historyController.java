package controller;

import java.sql.Date;
import java.time.LocalDate;

import model.cartsModel;
import model.historyModel;

public class historyController {
	
	private historyModel history;

	public historyController() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean insert(String TransactionId, String userId, String date ) {		
		history = new historyModel(TransactionId, userId, date);
		boolean inserted = history.insert();
		
		return inserted;		
	}
}
