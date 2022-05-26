package Customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Vector;
import java.util.Date;  

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Admin.manageGame;
import connect.Connect;
import controller.cartsController;
import controller.historyController;
import controller.manageGameController;
import userCheck.userChecking;

public class BuyGame extends JInternalFrame implements ActionListener, MouseListener {

	//Panel
	JPanel northPanel, northPanelTop, centerPanel,mainCenterPanel, centerPanelBottom,
	southPanel, southPanelBottom;
	
	//Table
	JTable table, table2;
	JScrollPane scrollPane, scrollPane2;
	DefaultTableModel dtm, dtm2;
	
	//JLabel
	JLabel title, idLbl, nameLbl, typeLbl, priceLbl, stockLbl, quantityLbl;
	
	//JTextField
	JTextField idTxt, nameTxt, typeTxt, priceTxt, stockTxt;
	
	//JSpinner
	JSpinner quantityS;
	
	//JButton
	JButton addBtn, removeBtn, clearBtn, checkoutBtn;
	
	Vector<Object> cartList;
	
	//user input
	userChecking userCheck;
	
	//Id generate
	int digit = 0;
	String TransactionID = "TR00" + digit;
	
	//Sub total
	int subTotal = 0;
		
	int selected_row, selected_row2;
	
	cartsController cartsGame;
	private Connect con = Connect.getInstance();
	private cartsController carts;
	
	historyController history;
	
	
	
	void initFrame() {
		
		
		// Init Internal Frame		
				setTitle("Buy Game");
				setSize(1440,860);
				setClosable(true);
				setVisible(true);
				setMaximizable(true);
				isMaximum();
				
		//Add panel
				add(northPanel, BorderLayout.NORTH);
				add(mainCenterPanel, BorderLayout.CENTER);
				add(southPanel, BorderLayout.SOUTH);
	}
	
	void Idstring() {
		TransactionID = "TR00" + digit;
	}
	
	void userId() {
		String query = "SELECT TransactionID FROM headertransactions "
				+ "WHERE TransactionID = (SELECT max(TransactionID) FROM headertransactions)";
		ResultSet rs = con.getData(query);			
		 try {
			while(rs.next()) {
			String id = rs.getString("TransactionID"),
				 digitId = id.charAt(4) + "";	
			 digit = Integer.parseInt(digitId) + 1;
			 }			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
	}
	
	LocalDate getDate() {		
		Date d = new Date();
		LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return date;	
	}

	
	void initTable1() {
		//Init object
				Object [] header = {"Game ID", "Game Name", "Game Type", "Game Price", "Game Stock"};

		// Init Default Table Model
				dtm = new DefaultTableModel(header, 0) {
					@Override
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
					
					if(stock <= 0) {
						continue;
					}
					
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
				
				
				
		//Init Table		
				table = new JTable(dtm);	
				table.getTableHeader().setReorderingAllowed(false);	
				
		// Init ScrollPane		
				scrollPane = new JScrollPane(table);
				scrollPane.setPreferredSize(new Dimension(60,200));				
	}
	
	
	void initTable2() {
		//Init object and table south
				Object [] header2 = {"Game ID", "Game Name", "Game Type", "Game Price", "Game Stock"
						, "Game Quantity", "Sub Total"};
																	
				// init Default Table Model
				dtm2 = new DefaultTableModel(header2, 0) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};								
				
				table2 = new JTable(dtm2);	
				table2.getTableHeader().setReorderingAllowed(false);	
				
				scrollPane2 = new JScrollPane(table2);
				scrollPane2.setPreferredSize(new Dimension(100,200));
	}
		
	public BuyGame() {		
		userId();
		Idstring();

		initTable1();
		initTable2();
		
//North
		title = new JLabel("Buy Game", SwingConstants.CENTER);
						
		northPanel = new JPanel(new BorderLayout());
		northPanel.add(title, BorderLayout.NORTH);
		northPanel.add(scrollPane);
		northPanel.setBackground(Color.yellow);
		
//centerPanelBottom
		addBtn = new JButton("Add to Cart");
		addBtn.setPreferredSize(new Dimension(170,40));
		centerPanelBottom = new JPanel();
		centerPanelBottom.add(addBtn);
		centerPanelBottom.setBorder(new EmptyBorder(5,5,5,5));
		centerPanelBottom.setBackground(Color.yellow);
		
//Center
		//Label
		idLbl = new JLabel("Game ID");
		nameLbl = new JLabel("Game Name");
		typeLbl = new JLabel("Game Type");
		priceLbl = new JLabel("Game Price");
		stockLbl = new JLabel("Game Stock");
		quantityLbl = new JLabel("Game Quantity");
		
		//TextField
		idTxt = new JTextField();
		idTxt.setEnabled(false);
		nameTxt = new JTextField();
		nameTxt.setEnabled(false);
		typeTxt = new JTextField();
		typeTxt.setEnabled(false);
		priceTxt = new JTextField();
		priceTxt.setEnabled(false);
		stockTxt = new JTextField();
		stockTxt.setEnabled(false);
		
		//Spinner
		quantityS = new JSpinner();
				
		centerPanel =  new JPanel(new GridLayout(3,4));
		centerPanel.add(idLbl);
		centerPanel.add(idTxt);
		
		centerPanel.add(priceLbl);
		centerPanel.add(priceTxt);
				
		centerPanel.add(nameLbl);
		centerPanel.add(nameTxt);
		
		centerPanel.add(stockLbl);
		centerPanel.add(stockTxt);
			
		centerPanel.add(typeLbl);
		centerPanel.add(typeTxt);
	
		centerPanel.add(quantityLbl);
		centerPanel.add(quantityS);
				
		centerPanel.setBackground(Color.yellow);
		


//mainCenterPanel		
		mainCenterPanel = new JPanel(new BorderLayout());
		mainCenterPanel.add(centerPanel, BorderLayout.CENTER);
		mainCenterPanel.add(centerPanelBottom, BorderLayout.SOUTH);
		mainCenterPanel.setBorder(new EmptyBorder(10,10,5,5));
		mainCenterPanel.setBackground(Color.yellow);
		
		
//southPanelBottom
		removeBtn = new JButton("Remove Selected cart");
		clearBtn = new JButton("Clear Cart"); 
		checkoutBtn= new JButton("Checkout");
		
		southPanelBottom = new JPanel(new GridLayout(1,3,20,20));
		southPanelBottom.add(removeBtn);
		southPanelBottom.add(clearBtn);
		southPanelBottom.add(checkoutBtn);
		southPanelBottom.setBackground(Color.yellow);
			
//South
		southPanel = new JPanel(new BorderLayout(5,5));
		southPanel.add(scrollPane2);
		southPanel.add(southPanelBottom, BorderLayout.SOUTH);
		southPanel.setBorder(new EmptyBorder(5,5,5,5));
		southPanel.setBackground(Color.yellow);
					
		
		//ActionListener		
		table.addMouseListener(this);
		table2.addMouseListener(this);
		addBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		removeBtn.addActionListener(this);
		checkoutBtn.addActionListener(this);
			
		initFrame();
		
	}

	void dataCart(String id, String name, String type, String price, String stock, int quantity) {		
		cartList.add(id);
		cartList.add(name);
		cartList.add(type);
		cartList.add(price);
		cartList.add(stock);
		cartList.add(quantity);					
	}
	
	void clearCart(){			
		int row = dtm2.getRowCount();
		int choose = JOptionPane.showOptionDialog(null, "Are you sure want to clear cart?"
				, "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
				, null, null, 0);	
		if(choose == 0) {
			for (int i = 0; i < row; i++) {
				dtm2.removeRow(0);
			}
			
			cartsGame = new cartsController();
			cartsGame.clearCart();
			JOptionPane.showMessageDialog(null, "Successfully clear cart");
		}
		
		
	}
	
	
	void removeCart(){	
		String id = dtm2.getValueAt(selected_row2, 0).toString();
		System.out.println(id);
		
		if(selected_row2 != -1) {
		int choose = JOptionPane.showOptionDialog(null, "Are you sure want to remove selected cart?"
				, "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
				, null, null, 0);	
		if(choose == 0) {
			dtm2.removeRow(selected_row2);
			carts = new cartsController();
			carts.delete(id);
			JOptionPane.showMessageDialog(null, "Remove cart success");
		}
		}
		else {
			JOptionPane.showMessageDialog(null, "Select data to delete !!");
		}	
		//clearInsert();
	}
	
	void checkout() {
	
//Database game update		
		for(int i = 0 ; i < dtm2.getRowCount() ; i++) {
			
		String stock = dtm2.getValueAt(i,4).toString();
		int stockDigit = Integer.parseInt(stock);		
						
		String gameId = dtm2.getValueAt(i,0).toString();
		String gameQuantity = dtm2.getValueAt(i,5).toString();
		int quantity = Integer.parseInt(gameQuantity);	
		stockDigit = stockDigit - quantity;
		
		
		String query = String.format("UPDATE games SET GameStock = '%d' "
				+ "WHERE GameID = '%s'", stockDigit, gameId);
		con.executeUpdate(query);
		
		}
		
		
	//Transaction detail	
		String userId = userCheck.Id,
				Id = TransactionID, date = getDate().toString();
		history = new historyController();
		history.insert(Id, userId, date);
		
		int row = dtm2.getRowCount();
			for (int i = 0; i < row; i++) {
				dtm2.removeRow(0);
			}
			
			cartsGame = new cartsController();
			cartsGame.clearCart();
			JOptionPane.showMessageDialog(null, "Successfully checkout cart");	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addBtn) {
			
			if(table.isRowSelected(selected_row)) {				
				String stock = dtm.getValueAt(selected_row,4).toString();
				int stockDigit = Integer.parseInt(stock);
				
				userCheck = new userChecking();
				cartList = new Vector();				
				String userId = userCheck.Id,
						name = dtm.getValueAt(selected_row,0).toString();
				int quantity = (Integer) quantityS.getValue();		
				
				if(quantity <= 0) {
					JOptionPane.showMessageDialog(null, "Please select quantity!");
				}
				
				else if(quantity > stockDigit) {
					JOptionPane.showMessageDialog(null, "There is no more stock for this game!");
				}
				
				else {																						
				//Database cart insert
				cartsGame = new cartsController();
				cartsGame.insert(userId, name, quantity);
				
				String idCart = idTxt.getText(),
						nameCart = nameTxt.getText(),
						typeCart = typeTxt.getText(),
						priceCart = priceTxt.getText(),
						stockCart = stockTxt.getText();
				
				int price = Integer.parseInt(priceCart);
				
				if(quantity == 0) {
				subTotal = quantity + price;
				}
				else {
				subTotal = quantity * price;
				}					
				dataCart(idCart, nameCart, typeCart, priceCart, stockCart, quantity);
				cartList.add(subTotal);
				dtm2.addRow(cartList);
				JOptionPane.showMessageDialog(null, "Successfully Insert Cart!");									
			}
			}
			else {
				JOptionPane.showMessageDialog(null, "Please select the game!");	
			}
			
		}
		
		else if(e.getSource() == checkoutBtn) {		
			checkout();
			digit++;
			Idstring();		
		}
		
		else if(e.getSource() == clearBtn) {
			clearCart();
		}
		
		else if(e.getSource() == removeBtn) {
			removeCart();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		selected_row = table.getSelectedRow();
		idTxt.setText(dtm.getValueAt(selected_row,0).toString());
		nameTxt.setText(dtm.getValueAt(selected_row,1).toString());
		typeTxt.setText((dtm.getValueAt(selected_row,2).toString()));
		priceTxt.setText(dtm.getValueAt(selected_row,3).toString());		
		stockTxt.setText((dtm.getValueAt(selected_row,4).toString()));	
		
		selected_row2 = table2.getSelectedRow();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
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
