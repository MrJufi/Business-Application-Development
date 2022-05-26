package Customer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Admin.editProfile;
import userCheck.userChecking;


public class customerMainMenu extends JFrame implements ActionListener {
	
	//Dektop
	JDesktopPane desktop = new JDesktopPane();
	
	//Menu
	JMenu profile, transaction;
	JMenuBar menuBar;
	
	//Profile Item
	JMenuItem profileEdit, exitProfile;
	
	//Transaction Item
	JMenuItem buyGame, history;
	
	JLabel title;
	JPanel centerPanel = new JPanel(new BorderLayout());
	
	userChecking userCheck;
	
	
	public customerMainMenu() {		
		userCheck = new userChecking();
		String titleName = "Welcome to DoPe gamestore " + userChecking.username;
		
		title = new JLabel(titleName, SwingConstants.CENTER);
		title.setFont(new Font(null, Font.BOLD, 25));
		centerPanel.add(title);
		centerPanel.setBackground(Color.yellow);
				
//Init menubar
		
	//Menu Item
		//Profile	
		profileEdit = new JMenuItem("Edit Profile");
		exitProfile = new JMenuItem("Exit");
	
		//Transaction
		buyGame = new JMenuItem("Buy Game");
		history = new JMenuItem("View Transaction History");
		
	//Menu add
		//Profile
		profile = new JMenu("Profile");
		profile.add(profileEdit);
		profile.add(exitProfile);
		
		//Transaction		
		transaction = new JMenu("Transaction");
		transaction.add(buyGame);
		transaction.add(history);
		
	//Menu Bar
		menuBar = new JMenuBar();
		menuBar.add(profile);
		menuBar.add(transaction);
		
		
	//MouseListener
		buyGame.addActionListener(this);
		history.addActionListener(this);
		profileEdit.addActionListener(this);
		exitProfile.addActionListener(this);
		
		
// Frame 
		setTitle("DoPe Game Store");
		setSize(1450,870);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		setResizable(true);
		setVisible(true);
		setLocationRelativeTo(null);
		add(centerPanel);
		setJMenuBar(menuBar);				
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buyGame) {
		getContentPane().removeAll();
		desktop.add(new BuyGame());	
		setContentPane(desktop);		
		}
		
		else if(e.getSource() == history) {	
		getContentPane().removeAll();
		desktop.add(new History());
		setContentPane(desktop);
		
		}
		
		else if(e.getSource() == exitProfile) {
		System.exit(0);	
		}
		
		else if (e.getSource() == profileEdit) {
			getContentPane().removeAll();
			desktop.add(new editProfile());	
			setContentPane(desktop);
		}
		
	}


	
}