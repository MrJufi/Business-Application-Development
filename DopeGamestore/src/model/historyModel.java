package model;

import java.sql.Date;
import java.time.LocalDate;

import connect.Connect;

public class historyModel {

	private String transactionId;
	private String userId;
	private String transactionDate;
	
	private Connect con = new Connect();	
	
	public historyModel() {
		// TODO Auto-generated constructor stub
	}
	
	public historyModel(String transactionId, String userId, String transactionDate) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.transactionDate = transactionDate;
	}

	public boolean insert() {			
		String query = String.format("INSERT INTO headertransactions VALUES ('%s', '%s', '%s')"
				, transactionId, userId, transactionDate);		
		if(con.updateData(query) != 0) {
			return true;
		}	
		return false;	
	}
}
