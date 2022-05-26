package Customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connect.Connect;

public class History extends JInternalFrame implements MouseListener{

	//Panel
	JPanel northPanel, centerPanel,
	southPanel;
	
	//Panel komponen
	JPanel selectedPanel, grandTotalPanel;
	
	//Table
	JTable table, table2;
	JScrollPane scrollPane1, scrollPane2;
	DefaultTableModel dtm, dtm2;
	
	//JLabel
	JLabel title, selectedLbl, grandTotalLbl;
	
	//JTextField
	JTextField selectedTxt, grandTotalTxt;
	
	int selected_row;
	
	
	
	//Detail transaction
	String trid;
	int grandTotal = 0;
	int subTotal = 0;
	private Connect con = Connect.getInstance();
	
	
	void initFrame() {
		// Init Internal Frame		
		setTitle("Buy Game");
		setSize(1440,860);
		setClosable(true);
		setVisible(true);
		setMaximizable(true);
		setBackground(Color.yellow);
		isMaximum();
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

	}
	
	void initTable1() {	
		//Init object
		Object [] header = {"Transaction ID", "User ID", "Transaction Date"};

		
		// Init Default Table Model
		dtm = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		
		String query = String.format("SELECT * FROM headertransactions");
		con.executeQuery(query);
		ResultSet rs = con.executeQuery(query);	
		
		Vector<Object> historyList;			
		try {
			while(rs.next()) {
				historyList = new Vector();
				
			String Transactionid = rs.getString("TransactionID"),
					userId = rs.getString("UserID"),
					date = rs.getString("TransactionDate");
			
			historyList.add(Transactionid);
			historyList.add(userId);
			historyList.add(date);
			
			dtm.addRow(historyList);
			 }	 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Init Table
		table = new JTable(dtm);
		table.getTableHeader().setReorderingAllowed(false);
		
		//Init Scrollpane
		scrollPane1 = new JScrollPane(table);	
		scrollPane1.setPreferredSize(new Dimension(100,300));	
	}

	void initTable2() {
		//Init Object
		Object[] header2 = {
			"Transaction ID", "Game ID", "Game Name", "Game Type",
			"Game Price", "Game Quantity", "Sub Total"	
		};
	
		//Init default table
		dtm2 = new DefaultTableModel(header2, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		

		
		//Init table2
		table2 = new JTable(dtm2);
		table2.getTableHeader().setReorderingAllowed(false);
		
		//Init scroll pane
		scrollPane2 = new JScrollPane(table2);
		scrollPane2.setPreferredSize(new Dimension(100,200));	
	}
			
	void datatable2() {
		String query = String.format("SELECT * FROM detailtransactions dt JOIN "
				+ "games gm ON dt.GameID = gm.GameID WHERE dt.TransactionID = '%s'", trid);
		con.executeQuery(query);
		ResultSet rs = con.executeQuery(query);	

		Vector<Object> historyList;			
		try {
			while(rs.next()) {
			historyList = new Vector();
				
			String Transactionid = rs.getString("TransactionID"),
					id = rs.getString("GameID"),
					name = rs.getString("GameName"), 
					type = rs.getString("GameType"),
					price =  rs.getString("GamePrice"), 
					quantity = rs.getString("Quantity");
			
			subTotal = subTotal + (rs.getInt("Quantity") * rs.getInt("GamePrice"));
			grandTotal = grandTotal + subTotal;
			
			historyList.add(Transactionid);
			historyList.add(id);
			historyList.add(name);
			historyList.add(type);
			historyList.add(price);
			historyList.add(quantity);
			historyList.add(subTotal);
			
			dtm2.addRow(historyList);
			 }	 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	public History() {
		
		initTable1();
		initTable2();
	
// North
		//Label and textfield
		selectedTxt = new JTextField();
		selectedTxt.setEnabled(false);
		selectedTxt.setPreferredSize(new Dimension(250,20));	
		selectedLbl = new JLabel("Selected ID");
		title = new JLabel("Transaction History", SwingConstants.CENTER);
		
		//Panel komponen
		selectedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		selectedPanel.add(selectedLbl);	
		selectedPanel.add(selectedTxt);	
		selectedPanel.setBorder(new EmptyBorder(10,0,0,0));
		selectedPanel.setBackground(Color.yellow);
		
		//Panel
		northPanel = new JPanel(new BorderLayout());
		northPanel.add(title, BorderLayout.NORTH);
		northPanel.add(scrollPane1, BorderLayout.CENTER);
		northPanel.add(selectedPanel, BorderLayout.SOUTH);
		northPanel.setBorder(new EmptyBorder(0,5,0,5));
		northPanel.setBackground(Color.yellow);
		
//Center		
		//Panel
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(scrollPane2);
		centerPanel.setBorder(new EmptyBorder(20,5,0,5));
		centerPanel.setBackground(Color.yellow);
		
		
//South		
		//Label and textfield
		grandTotalLbl = new JLabel("Grand Total");
		grandTotalTxt = new JTextField("0");
		grandTotalTxt.setEnabled(false);
		grandTotalTxt.setPreferredSize(new Dimension(250,20));	
		
		//Panel Komponen
		grandTotalPanel = new JPanel();
		grandTotalPanel.add(grandTotalLbl);
		grandTotalPanel.add(grandTotalTxt);
		grandTotalPanel.setBackground(Color.yellow);
		
		//Panel
		southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		southPanel.add(grandTotalPanel);
		southPanel.setBorder(new EmptyBorder(0,0,20,0));
		southPanel.setBackground(Color.yellow);
				
		table.addMouseListener(this);		
		initFrame();

	}
	

	@Override
	public void mouseClicked(MouseEvent e) {	
		dtm2.setRowCount(0);
		subTotal = 0;
		grandTotal = 0;
				
		selected_row = table.getSelectedRow();
		int index = selected_row;	
		selectedTxt.setText(dtm.getValueAt(selected_row,0).toString());	
		trid = dtm.getValueAt(selected_row,0).toString();		
		datatable2();

		String gTotal = "" + grandTotal;
		grandTotalTxt.setText(gTotal);	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
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
		
	}

}
