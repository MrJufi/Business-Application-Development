package Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import MainGUI.Main;
import connect.Connect;
import controller.manageGameController;
import model.manageGameModel;
import model.userModel;

public class manageGame extends JInternalFrame implements ActionListener, MouseListener {
	
	//generate ID
	int digit = 0;
	String ID = "GA00" + digit;
	
	//Vector
	//game type
	Vector<String> typeCBData = new Vector<String>();
	//new game type
	Vector<String> newTypeCBData = new Vector<String>();
	
	manageGameController manageGame;
	private Connect con = Connect.getInstance();
	
	JDesktopPane desktop = new JDesktopPane();
	
	//Table
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel dtm;

	//Panel
	JPanel northPanel, centerPanel, southPanel;
	
	//Panel components
	JPanel addStockPanel, addBtnPanel;
	
	//Label
	JLabel title, IdLbl, nameLbl, typeLbl, priceLbl, stockLbl,
	newIdLbl, newNameLbl, newTypeLbl, newPriceLbl, newStockLbl, addLbl;
	
	//TextField
	JTextField IdTxt, nameTxt, priceTxt, stockTxt, 
	newIdTxt, newNameTxt, newPriceTxt, newStockTxt;
	
	//Spinner
	JSpinner addStockS, newStock;
	
	//Combo Box
	JComboBox typeCB, newTypeCB;
	
	//Button
	JButton insertBtn, updateBtn, deleteBtn, resetBtn, addBtn;
	
	int selected_row;
	
	
	
	void userId() {
		String query = "SELECT GameID FROM games "
				+ "WHERE GameID = (SELECT max(GameID) FROM games)";
		ResultSet rs = con.getData(query);			
		 try {
			while(rs.next()) {
			String id = rs.getString("GameID"),
				 digitId = id.charAt(4) + "";	
			 digit = Integer.parseInt(digitId) + 1;
			 }			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
	}
	
	void Idstring() {
		ID = "GA00" + digit;
	}
	
	void initFrame() {
		// Init Internal Frame		
		setTitle("Buy Game");
		setSize(1440,860);
		setClosable(true);
		setVisible(true);
		setMaximizable(true);
		isMaximum();
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
	}
	
	void initTable() {
		
		//Init Object
		Object[] header = {
			"Game ID", "Game Name", "Game Type",
			"Game Price", "Game Stock"	
		};
					
		//Init default table
		dtm = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		

		String query = String.format("SELECT * FROM games");
		con.executeQuery(query);
		ResultSet rs = con.executeQuery(query);	
		
		Vector<Object> manageGameList;			
		try {
			while(rs.next()) {
			manageGameList = new Vector();
				
			String id = rs.getString("GameID"),
					name = rs.getString("GameName"),
					type = rs.getString("GameType");
			int price = rs.getInt("GamePrice");
			int stock = rs.getInt("GameStock");		
			
			manageGameList.add(id);
			manageGameList.add(name);
			manageGameList.add(type);
			manageGameList.add(price);
			manageGameList.add(stock);
			
			dtm.addRow(manageGameList);
			 }	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		//Init table2
		table = new JTable(dtm);
		table.getTableHeader().setReorderingAllowed(false);
		
		//Init scroll pane
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(100,300));	
	}
	
	public manageGame() {
		
		userId();
		Idstring();
		
		initTable();			
//North
		//Label
		title = new JLabel("Manage Game", SwingConstants.CENTER);
		
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.add(title, BorderLayout.NORTH);		
		northPanel.add(scrollPane);		
		northPanel.setBackground(Color.yellow);	
		
//Center	
		//Label
		IdLbl = new JLabel("Game ID"); 
		nameLbl = new JLabel("Game Name"); 
		typeLbl = new JLabel("Game Type");
		priceLbl = new JLabel("Game Price");
		stockLbl = new JLabel("Game Stock");
		
		newIdLbl = new JLabel("New Game ID");
		newNameLbl = new JLabel("New Game Name");
		newTypeLbl = new JLabel("New Game Type");
		newPriceLbl = new JLabel("New Game Price"); 
		newStockLbl = new JLabel("New Game Stock");

		//TextField
		Idstring();
		IdTxt = new JTextField();
		IdTxt.setEnabled(false);
		nameTxt = new JTextField();
		priceTxt = new JTextField();
		stockTxt = new JTextField();
		stockTxt.setEnabled(false);
		
		newIdTxt = new JTextField(ID);
		newIdTxt.setEnabled(false);
		newNameTxt = new JTextField();
		newPriceTxt = new JTextField();
		
		//JSpinner
		newStock = new JSpinner();
		
		//Combo Box
		typeCB = new JComboBox<String>(typeCBData);
		newTypeCB = new JComboBox<String>(newTypeCBData);
		
	//add vector
		
		//game
		typeCBData.add("TPS");
		typeCBData.add("MOBA");
		typeCBData.add("FPS");
		typeCBData.add("RPG");
		
		//new Game
		newTypeCBData.add("TPS");
		newTypeCBData.add("MOBA");
		newTypeCBData.add("FPS");
		newTypeCBData.add("RPG");
				
		//Panel components
		centerPanel = new JPanel(new GridLayout(5,4));
		centerPanel.add(newIdLbl);
		centerPanel.add(newIdTxt);
		centerPanel.add(IdLbl);
		centerPanel.add(IdTxt);
		centerPanel.add(newNameLbl);
		centerPanel.add(newNameTxt);
		centerPanel.add(nameLbl);
		centerPanel.add(nameTxt);	
		centerPanel.add(newTypeLbl);
		centerPanel.add(newTypeCB);
		centerPanel.add(typeLbl);
		centerPanel.add(typeCB);
		centerPanel.add(newPriceLbl);
		centerPanel.add(newPriceTxt);
		centerPanel.add(priceLbl);
		centerPanel.add(priceTxt);
		centerPanel.add(newStockLbl);
		centerPanel.add(newStock);
		centerPanel.add(stockLbl);
		centerPanel.add(stockTxt);
		
		centerPanel.setBackground(Color.yellow);
			
//South
		
	//GroupComponents
		addStockS = new JSpinner();
		addStockS.setPreferredSize(new Dimension(200,30));
		addLbl = new JLabel("Add Stock");
		addStockPanel = new JPanel(new GridLayout(1,2));
		addStockPanel.add(addLbl);
		addStockPanel.add(addStockS);
		addStockPanel.setBackground(Color.yellow);
		
		//Button
		insertBtn = new JButton("Insert Game");
		updateBtn = new JButton("Update Game");
		deleteBtn = new JButton("Delete Game");
		resetBtn = new JButton("Reset");
		addBtn = new JButton("Add Stock");
		addBtnPanel = new JPanel();
		addBtnPanel.add(addBtn);
		addBtnPanel.setBackground(Color.yellow);
			
		
		southPanel = new JPanel(new GridLayout(2,3,20,20));
		southPanel.add(insertBtn);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);
		southPanel.add(resetBtn);
		southPanel.add(addStockPanel);
		southPanel.add(addBtnPanel);
		southPanel.setBorder(new EmptyBorder(10,10,10,10));	
		southPanel.setBackground(Color.yellow);
		
		
		//action listener
		insertBtn.addActionListener(this);		
		updateBtn.addActionListener(this);		
		deleteBtn.addActionListener(this);		
		addBtn.addActionListener(this);		
		resetBtn.addActionListener(this);		
		table.addMouseListener(this);
		initFrame();
	}
	
	boolean validasiNum(String price) {
		int priceint;
		try{			
			priceint = Integer.parseInt(price); 
			return false;
		}catch(Exception e) {
			return true;
		}		
	}
	
	void insertData(){
		String id = newIdTxt.getText(),
				name = newNameTxt.getText(),
				price = newPriceTxt.getText(),			
				type = null;
		int stock = (Integer) newStock.getValue();
		
		int priceInt = 0;		
		
		try{			
			type = newTypeCB.getSelectedItem().toString();		
			}catch(Exception e) {
		}
				
		try{			
		priceInt = Integer.parseInt(price);	
		}catch(Exception e) {			
		}
		
		if(name.equals("") || price.equals("")) {
			JOptionPane.showMessageDialog(null, "All field must be completed");
		}
		
		else if(name.length() < 5 || name.length() > 30) {
			JOptionPane.showMessageDialog(null, "Username must consist 5 - 30 characters");
		}
		
		else if(validasiNum(price)) {
			JOptionPane.showMessageDialog(null, "Price / Stock must all numeric");
		}	
		
		else if(priceInt < 0) {
			JOptionPane.showMessageDialog(null, "Price Must more than 0");
		}
		
		else if(stock < 0) {
			JOptionPane.showMessageDialog(null, "Stock Must more than 0");
		}
		
		else {
			
		int choose = JOptionPane.showOptionDialog(null, "Are you sure want to insert new game?"
					, "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
					, null, null, 0);
		
		if(choose  == 0) {
		dtm.addRow(new Object[] {id, name, type, price, stock});
		
		manageGame = new manageGameController();
		manageGame.insert(id, name, type, priceInt, stock);		
		JOptionPane.showMessageDialog(null, "Insert Successfully");
		
		digit++;
		Idstring();
		newIdTxt.setText(ID);
		}
		clearnewInsert();
		
		}	
	}
	
	void addStock() {
		String id = dtm.getValueAt(selected_row,0).toString();
		int stockBefore = (Integer)dtm.getValueAt(selected_row,4);
		int stock = (Integer) addStockS.getValue();
	
		
		if(stock < 0) {
			JOptionPane.showMessageDialog(null, "Stock must be more than 0");
		}
		
		else {
			
		int choose = JOptionPane.showOptionDialog(null, "Are you sure want to add game stock?"
					, "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
					, null, null, 0);
		if(choose == 0) {
		stock = stockBefore + (Integer) addStockS.getValue();
		String stockString = ""+ stock;
			
		table.setValueAt(stockString, selected_row, 4);
		manageGame = new manageGameController();
		manageGame.addStock(id, stock);
		JOptionPane.showMessageDialog(null, "Update Successfully");	
		}
		}
		clearInsert();
		
	}
	
	void updateData() {
		
		String id = dtm.getValueAt(selected_row,0).toString();
		String name = nameTxt.getText(),
				price = priceTxt.getText(),
				type = null;
		int priceInt = 0;
		
		try {
		type = typeCB.getSelectedItem().toString();
		}catch(Exception e) {
		}
		
		try{			
			priceInt = Integer.parseInt(price); 
		}catch(Exception e) {
		}		
					
		if(name.equals("") || price.equals("") || type.equals("")) {
			JOptionPane.showMessageDialog(null, "You must update all field");
		}
		
		else if(name.length() < 5 || name.length() > 30) {
			JOptionPane.showMessageDialog(null, "Name must consist of 5 - 30 characters");
		}
		
		else if(validasiNum(price) == true) {
			JOptionPane.showMessageDialog(null, "Price Must be numeric");
		}
		
		else if(priceInt < 0) {
			JOptionPane.showMessageDialog(null, "Price Must more than 0");
		}
		
		else {
			int choose = JOptionPane.showOptionDialog(null, "Are you sure want to update game?"
					, "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
					, null, null, 0);
			if (choose == 0) {
			table.setValueAt(name, selected_row, 1);
			table.setValueAt(type, selected_row, 2);
			table.setValueAt(price, selected_row, 3);
			manageGame = new manageGameController();
			manageGame.update(id, name, type, priceInt);
			JOptionPane.showMessageDialog(null, "Update Successfully");
			}			
		}
		clearInsert();
		
	}
	
	void deleteData(){	
		String id = dtm.getValueAt(selected_row,0).toString();
		if(selected_row != -1) {
		int choose = JOptionPane.showOptionDialog(null, "Are you sure want to delete game?"
				, "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
				, null, null, 0);	
		if(choose == 0) {
			dtm.removeRow(selected_row);
			manageGame = new manageGameController();
			manageGame.delete(id);
			JOptionPane.showMessageDialog(null, "Delete data success");
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "Select data to delete !!");
		}	
		clearInsert();
	}
		
	void clearnewInsert() {	
		newNameTxt.setText("");
		newTypeCB.setSelectedIndex(0);
		newPriceTxt.setText("");
		newStockTxt.setText("");;		
	}
	
	void clearInsert() {	
		nameTxt.setText("");
		typeCB.setSelectedIndex(0);
		priceTxt.setText("");
		stockTxt.setText("");		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertBtn) {
			insertData();			
	}
		else if (e.getSource() == updateBtn){
			updateData();
		}
		
		else if (e.getSource() == deleteBtn){
			deleteData();
		}
		
		else if (e.getSource() == addBtn){
			addStock();
		}
		
		else if (e.getSource() == resetBtn){		
			clearnewInsert();
			
		}
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		selected_row = table.getSelectedRow();
		IdTxt.setText(dtm.getValueAt(selected_row,0).toString());
		nameTxt.setText(dtm.getValueAt(selected_row,1).toString());
		typeCB.setSelectedItem((dtm.getValueAt(selected_row,2).toString()));
		priceTxt.setText(dtm.getValueAt(selected_row,3).toString());		
		stockTxt.setText((dtm.getValueAt(selected_row,4).toString()));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}