package MainGUI;

import MainGUI.*;
import connect.Connect;
import controller.userController;
import model.manageGameModel;
import model.userModel;
import userCheck.userChecking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.AncestorListener;

import Admin.adminMainMenu;
import Customer.customerMainMenu;

public class Main extends JFrame implements ActionListener, MouseListener {

	//Database connect
	private Connect con = new Connect();
	
	
//Initialize Login Form
	JLabel title, signUp, emailLbl, passwordLbl;
	JTextField emailTxt;
	JPasswordField passwordFd;
	JButton loginBtn;
	
	//panel
	JPanel mainPanel, northPanel, centerPanel, southPanel, eastPanel,
	westPanel;
	
	//Panel Components
	JPanel emailTxtPanel, passwordFdPanel, btnPanel, signUpPanel;
	
	//Insert ID and Password
	String userName, password, userID, email;
	
	userChecking us;
	
	public Main() {
		LoginFrame();	
	}	
	
	void validasiIdandPw(){	
		
		boolean optionPaneCheck = false;
		String email = emailTxt.getText(),
		password = passwordFd.getText();
		if(email.equals("") || password.equals("")) {
			JOptionPane.showMessageDialog(null, "Email / password must be filled");
		}

		else {				
			String query = "SELECT * FROM users";
			ResultSet rs = con.getData(query);		
			Vector<userModel> userList = new Vector<userModel>();			
			 try {
				while(rs.next()) {
				String id = rs.getString("UserID"),
					username = rs.getString("UserName"),
					uemail = rs.getString("UserEmail"),
					passwordUser = rs.getString("UserPassword"),
					date = rs.getString("UserDOB"),
					gender = rs.getString("UserGender"),
					address = rs.getString("UserAddress"),
					phone = rs.getString("UserPhone"),
					role = rs.getString("UserRole");			
				userList.add(new userModel(id, username, uemail, passwordUser, date, gender, address, phone, role));		
				
				 }			 
			} catch (Exception e) {
				e.printStackTrace();
			}
			 for(userModel user : userList) {
					String emailCheck = user.getEmail(),
							passwordCheck = user.getPassword();
					if(email.equals(emailCheck) && password.equals(passwordCheck)) {						
						us = new userChecking();
						us.username = user.getUsername();
						us.Id = user.getId();
						us.role = user.getRole();
						
						
						//Role check
						if(user.getRole().equals("Admin")) {
						new adminMainMenu();								
						dispose();													
						}
						
						else if(user.getRole().equals("Customer")) {
							System.out.println("tes");
						new customerMainMenu();
						dispose();
						}
						
					optionPaneCheck = false;					
					break;
					
					}
					else {
					optionPaneCheck = true;
					}									
				}	
			 if(optionPaneCheck) {
				JOptionPane.showMessageDialog(null, "Wrong email / password!");	
			 }
		}
	}
		
		
	void LoginFrame() {
		
//Init component 	
		//JLabel
		title = new JLabel("Login Form");
		title.setFont(new Font(null, Font.BOLD, 25));
		signUp = new JLabel("Sign Up Here");
		signUpPanel = new JPanel();
		signUpPanel.add(signUp);
		signUpPanel.setBackground(Color.yellow);
		emailLbl = new JLabel("Email");
		passwordLbl = new JLabel("Password");
				
		//JTextField
		emailTxt = new JTextField();
		emailTxt.setPreferredSize(new Dimension (200,20));
		emailTxtPanel = new JPanel();
		emailTxtPanel.setBackground(Color.yellow);
		emailTxtPanel.add(emailTxt);
		
		//JPasswordField 
		passwordFd = new JPasswordField();
		passwordFd.setPreferredSize(new Dimension (200,20));
		passwordFdPanel = new JPanel();
		passwordFdPanel.add(passwordFd);
		passwordFdPanel.setBackground(Color.yellow);
		
		//JButton
		 loginBtn= new JButton("Login");
		 btnPanel = new JPanel();
		 btnPanel.add(loginBtn);
		 btnPanel.setBackground(Color.yellow);
		 		 							
	//North panel
		northPanel = new JPanel();
		northPanel.add(title);
		northPanel.setBackground(Color.yellow);
	
	//Center Panel
		centerPanel = new JPanel(new GridLayout(2,2));
		centerPanel.add(emailLbl);
		centerPanel.add(emailTxtPanel);
		centerPanel.add(passwordLbl);
		centerPanel.add(passwordFdPanel);
		centerPanel.setBackground(Color.yellow);
		
	// South Panel
		southPanel = new JPanel(new GridLayout(2,1) );		
		southPanel.add(btnPanel);
		southPanel.add(signUpPanel);
		southPanel.setBackground(Color.yellow);
		
	//Sisa panel
		eastPanel = new JPanel();
		eastPanel.setBackground(Color.yellow);
		westPanel = new JPanel();
		westPanel.setBackground(Color.yellow);
	
	// Main Panel
		mainPanel = new JPanel(new BorderLayout(20,20));	
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(westPanel, BorderLayout.WEST);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		mainPanel.setBackground(Color.yellow);

	//Action Listener
		 loginBtn.addActionListener(this);
		 signUp.addMouseListener(this);
		 											
// Frame 
		setTitle("Login Form");
		setSize(500,250);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		setResizable(true);
		setVisible(true);
		setLocationRelativeTo(null);
		add(mainPanel);
	}
	
	public static void main(String[] args) {
		new Main();

	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == loginBtn) {				
			validasiIdandPw();
		}	
	}		
	

	@Override
	public void mouseClicked(MouseEvent e) {	
		new registerClass();	
		dispose();
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
		// TODO Auto-generated method stub
		
	}
}
