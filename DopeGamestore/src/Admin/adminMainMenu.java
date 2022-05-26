package Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import Customer.BuyGame;
import MainGUI.Main;
import userCheck.userChecking;

public class adminMainMenu extends JFrame implements ActionListener {
	
	//Menubar
	JMenu profile, manage;
	JMenuBar menuBar;
	
	//Profile Item
	JMenuItem profileEdit, exitProfile;
	
	//Manage Item
	JMenuItem manageItem;
	
	//Label
	JLabel title;
	
	//Panel
	JPanel centerPanel = new JPanel(new BorderLayout());
	
	//DesktopPane
	JDesktopPane desktop = new JDesktopPane();
	
	userChecking userCheck;

	
	
	public adminMainMenu() {	
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
		manageItem = new JMenuItem("Manage Game");
		
	//Menu add
		//Profile
		profile = new JMenu("Profile");
		profile.add(profileEdit);
		profile.add(exitProfile);
		
		//Transaction		
		manage = new JMenu("Manage");
		manage.add(manageItem);
		
	//Menu Bar
		menuBar = new JMenuBar();
		menuBar.add(profile);
		menuBar.add(manage);
	
	
	//Action Listener
		manageItem.addActionListener(this);
		exitProfile.addActionListener(this);
		profileEdit.addActionListener(this);
		
		
		
		// Frame 
				setTitle("DoPe Game Store");
				setSize(1450,890);		
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
				setResizable(true);
				setVisible(true);
				setLocationRelativeTo(null);
				add(centerPanel);			
				setJMenuBar(menuBar);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == manageItem) {
			getContentPane().removeAll();
			desktop.add(new manageGame());	
			setContentPane(desktop);		
		}
		
		else if (e.getSource() == exitProfile) {
			System.exit(0);	
		}
		
		else if (e.getSource() == profileEdit) {
			getContentPane().removeAll();
			desktop.add(new editProfile());	
			setContentPane(desktop);
		}
		
	}
	
}