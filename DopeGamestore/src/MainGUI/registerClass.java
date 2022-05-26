package MainGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.*;

import connect.Connect;
import model.userModel;

public class registerClass extends JFrame implements MouseListener, ActionListener {
		
//Vector
	Vector<String> pilihanRole = new Vector<String>();
	
	//generate ID
	int digit = 0;
	String ID = "";
		
// connector
	private Connect con = Connect.getInstance();
	

//Panel Penampung
	JPanel northPanel, centerPanel, southPanel,eastPanel,
	westPanel, mainPanel;
		
	//Panel Components
	JPanel genderPanel, SignInPanel, IdTxtPanel, userNameTxtPanel,
	emailTxtPanel, phoneTxtPanel, passwordFdPanel;
		
//Components
	//JLabel
	JLabel registerTitle, IdLbl, userNameLbl, emailLbl, phoneLbl, addressLbl,
	passwordLbl, genderLbl, roleLbl,signInLbl;
	
	//JTextField
	JTextField IdTxt, userNameTxt, emailTxt, phoneTxt;
	
	//JPasswordField
	JPasswordField passwordFd;
	
	//JTextArea
	JTextArea addressTxtA;
	
	//JRadioButton
	JRadioButton female, male;
	
	//JComboBox
	JComboBox roleCB;
	
	//group button
	ButtonGroup GroupBtn;
	
	//JButton
	JButton register;
	
	public registerClass() {
		//Generate ID
		userId();
		Idstring();
		
	//Add Vector
		pilihanRole.add("Admin");
		pilihanRole.add("Customer");
	
//North
	//Components
		registerTitle = new JLabel("Register");
		registerTitle.setFont(new Font(null, Font.BOLD, 25));
		//Panel
		northPanel = new JPanel();
		northPanel.add(registerTitle);

//Center
	//Components
		//Label
		IdLbl = new JLabel("ID");
		userNameLbl = new JLabel("User Name");
		emailLbl = new JLabel("Email");
		phoneLbl = new JLabel("Phone");
		addressLbl = new JLabel("Address");
		passwordLbl = new JLabel("Password");
		genderLbl = new JLabel("Gender");
		roleLbl = new JLabel("Role");
		
		//JTextField
		IdTxt = new JTextField(ID);
		IdTxt.setEnabled(false);
		IdTxt.setPreferredSize(new Dimension (210,20));
		
		userNameTxt = new JTextField();
		userNameTxt.setPreferredSize(new Dimension (210,20));
		
		emailTxt = new JTextField();
		emailTxt.setPreferredSize(new Dimension (210,20));
		
		phoneTxt = new JTextField();
		phoneTxt.setPreferredSize(new Dimension (210,20));
		
		//JTextArea
		addressTxtA = new JTextArea();
		
		//JPasswordField
		passwordFd = new JPasswordField();
		passwordFd.setPreferredSize(new Dimension (210,20));
		
		//ComboBox
		roleCB = new JComboBox<String>(pilihanRole);		
		
		//JRadioButton
		female = new JRadioButton("Female");
		female.setActionCommand("Female");
		male = new JRadioButton("Male");
		male.setActionCommand("Male");
		genderPanel = new JPanel();
		genderPanel.add(female);
		genderPanel.add(male);
		
		//Button group
		GroupBtn = new ButtonGroup();
		GroupBtn.add(female);
		GroupBtn.add(male);
		
		//Panel Components
		IdTxtPanel = new JPanel();
		IdTxtPanel.add(IdTxt);
		
		userNameTxtPanel = new JPanel();
		userNameTxtPanel.add(userNameTxt);
		
		emailTxtPanel = new JPanel();
		emailTxtPanel.add(emailTxt);
		
		phoneTxtPanel = new JPanel();
		phoneTxtPanel.add(phoneTxt);
		
		passwordFdPanel = new JPanel();
		passwordFdPanel.add(passwordFd);
							
		//Panel
		centerPanel = new JPanel(new GridLayout(8,2));
		centerPanel.add(IdLbl);
		centerPanel.add(IdTxtPanel);
		centerPanel.add(userNameLbl);
		centerPanel.add(userNameTxtPanel);
		centerPanel.add(emailLbl);
		centerPanel.add(emailTxtPanel);
		centerPanel.add(phoneLbl);
		centerPanel.add(phoneTxtPanel);
		centerPanel.add(addressLbl);
		centerPanel.add(addressTxtA);
		centerPanel.add(passwordLbl);
		centerPanel.add(passwordFdPanel);
		centerPanel.add(genderLbl);
		centerPanel.add(genderPanel);
		centerPanel.add(roleLbl);
		centerPanel.add(roleCB);
		
//West
		//Panel
		westPanel = new JPanel();

//East
		//Panel
		eastPanel = new JPanel();

//South
	//Components
		//Label		
		signInLbl = new JLabel("      Sign In");
		
		//JButton
		register = new JButton("Register");
		
		//Panel button sign in dan register	
		SignInPanel = new JPanel(new GridLayout(2,1));
		SignInPanel.add(register);
		SignInPanel.add(signInLbl);
				
		//Panel
		southPanel = new JPanel();
		southPanel.add(SignInPanel);

//MainPanel
		//Panel
		mainPanel = new JPanel(new BorderLayout(20,20));
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		mainPanel.add(westPanel, BorderLayout.WEST);
		mainPanel.add(eastPanel, BorderLayout.EAST);

		//MouseListener
		signInLbl.addMouseListener(this);
		
		//ActionListener
		register.addActionListener(this);
					
		setTitle("Register Form");
		setSize(500,500);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		setResizable(true);
		setVisible(true);
		setLocationRelativeTo(null);
		add(mainPanel);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
		Main main = new Main();		
		dispose();
	}
	
	void userId() {
		String query = "SELECT UserID FROM users WHERE UserID = (SELECT max(UserID) FROM users)";
		ResultSet rs = con.getData(query);			
		 try {
			while(rs.next()) {
			String id = rs.getString("UserID"),
				 digitId = id.charAt(4) + "";	
			 digit = Integer.parseInt(digitId) + 1;
			 }			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
	}
	
	boolean validNumeric(String phone) {
		int index = 0;
			
		for(int i = 0; i <  phone.length() ; i++ ) {
			if(Character.isDigit(phone.charAt(i))) {
				index++;
			}
		}		
		if(index == phone.length()) {
			return false;
		}
		else {
			return true;
		}		
	}
	
	void Idstring() {
		ID = "US00" + digit;
	}
	
	LocalDate getDate() {		
		Date d = new Date();
		LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return date;	
	}
	
	
	public boolean ValidPassword(String password) {		
		String j_password = password;
        String digit_password = j_password.replaceAll("[\\D]", "");
        if(digit_password.length() < 1){
            return true;
        }else if(j_password.length() < 5 || j_password.length() > 30){
            return true;
        }
        else {
        	return false;
        }
	}
		
	boolean validGender(String gender){
		//gender			
			try {			
			gender = GroupBtn.getSelection().getActionCommand();
			return false;

			} catch (Exception e1) {
			return true;
			}
			
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String username = userNameTxt.getText(),
				email = emailTxt.getText(),
				phone = phoneTxt.getText(),
				address = addressTxtA.getText(),
				password = passwordFd.getText(),
				gender = null,
				role = roleCB.getSelectedItem().toString(),
				date = getDate().toString();		
//Validation
	//username
		if(username.equals(""))
			JOptionPane.showMessageDialog(null, "Username must be filled");	

		else if(username.length() < 5 || username.length() > 30)
			JOptionPane.showMessageDialog(null, "Username length must be 5 - 30");

	//email
		else if(email.equals("")) 
			JOptionPane.showMessageDialog(null, "Email must be filled");	
		
		else if(!(email.contains("@")) || !((email.contains("."))))
			JOptionPane.showMessageDialog(null, "Please fill with valid email");	
		
	//phone number	
		else if(phone.equals(""))
			JOptionPane.showMessageDialog(null, "Phone number must be filled");
		
		else if(phone.length() != 12)
			JOptionPane.showMessageDialog(null, "Phone number must be 12 digits");
		
		else if(validNumeric(phone)) 
			JOptionPane.showMessageDialog(null, "Phone number must all numeric");			
		
	//address
		else if(address.equals("")) 
			JOptionPane.showMessageDialog(null, "Address must be filled");
			
		else if(address.length() < 10)
			JOptionPane.showMessageDialog(null, "Address must consist at least 10 characters");
		
		else if(!(address.endsWith("street")))
			JOptionPane.showMessageDialog(null, "Address must consist 'streets'");
		
	//Password
		else if(ValidPassword(password)){
			JOptionPane.showMessageDialog(null, "Password must 5 - 30 length of character and contain digit");
		}	
		
	//password
		else if(password.equals(""))
			JOptionPane.showMessageDialog(null, "Password must be filled");
		else if(password.length() < 5 || password.length() > 30)
			JOptionPane.showMessageDialog(null, "Password must be filled");
		
	//gender
		else if(validGender(gender))
			JOptionPane.showMessageDialog(null, "Gender must be choose !");
		
	//Success	
		else {	
		//gender data
		gender = GroupBtn.getSelection().getActionCommand().toString();			
		//insert data
			String query = String.format("INSERT INTO users VALUES ('%s', '%s', '%s', '%s', '%s'"
					+ ", '%s', '%s', '%s', '%s')", 
					ID,username, email, password, date,
					gender, address, phone, role);
			con.executeUpdate(query);
						
		//success message
			JOptionPane.showMessageDialog(null, "Successfully Registered");	
			
			Idstring();
			IdTxt.setText(ID);
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
