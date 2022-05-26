package Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Customer.History;
import connect.Connect;
import controller.editProfileController;
import userCheck.userChecking;

public class editProfile extends JInternalFrame implements ActionListener{

	//Panel
	JPanel northPanel, centerPanel, southPanel, mainPanel;
	
	//Panel Komponen
	JPanel radioBtn, updateBtnPanel;
	
	//Textfield
	JTextField NameTxt, EmailTxt, PhoneTxt;
	
	//Label
	JLabel title, NameLbl, EmailLbl, PhoneLbl, addressLbl, genderLbl;
	
	//TextArea
	JTextArea addressTA;
	
	//RadioButton
	JRadioButton male, female;
	
	//Button
	JButton updateBtn;
	
	//Button Group
	ButtonGroup gender;
	
	// profile update
	userChecking user;
	editProfileController profile;
	
	private Connect con = Connect.getInstance();
	
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

	public editProfile() {

//North
		//Label
		title = new JLabel("Update Profile");
		
		//Panel
		northPanel = new JPanel();
		northPanel.add(title);
		northPanel.setBackground(Color.yellow);

//Center	
		//label
		NameLbl = new JLabel("Username");
		EmailLbl = new JLabel("User Email");
		PhoneLbl = new JLabel("User Phone");
		addressLbl = new JLabel("User Address");
		genderLbl = new JLabel("User gender");
		
		//TextField
		NameTxt = new JTextField("");
		EmailTxt = new JTextField("");
		PhoneTxt = new JTextField("");
		
		//Text Area
		addressTA = new JTextArea();
		
		//Radio Button
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		
		//group button
		gender = new ButtonGroup();
		gender.add(male);
		gender.add(female);
		
		//Panel Comps
		radioBtn = new JPanel(new GridLayout(1,2));
		radioBtn.add(male);
		radioBtn.add(female);
		radioBtn.setBackground(Color.yellow);
		
		//Panel
		centerPanel = new JPanel(new GridLayout(5,2));
		centerPanel.add(NameLbl);
		centerPanel.add(NameTxt);
		centerPanel.add(EmailLbl);
		centerPanel.add(EmailTxt);
		centerPanel.add(PhoneLbl);
		centerPanel.add(PhoneTxt);
		centerPanel.add(addressLbl);
		centerPanel.add(addressTA);
		centerPanel.add(genderLbl);
		centerPanel.add(radioBtn);
		centerPanel.setBorder(new EmptyBorder(20,20,100,20));	
		centerPanel.setBackground(Color.yellow);	
		
//South
		//Button
		updateBtn = new JButton("Update Profile");
		updateBtn.setPreferredSize(new Dimension(800,50));
		
		//Panel Comps
		updateBtnPanel = new JPanel();
		updateBtnPanel.add(updateBtn);
		updateBtnPanel.setBackground(Color.yellow);
		
		//Panel
		southPanel = new JPanel();
		southPanel.add(updateBtnPanel);
		southPanel.setBorder(new EmptyBorder(0,0,20,00));
		southPanel.setBackground(Color.yellow);
		
		//action listener
		updateBtn.addActionListener(this);
		

		initFrame();
	}
	
	
	boolean validGender(String Gender){
		//gender			
			try {			
			Gender = gender.getSelection().getActionCommand();
			return false;

			} catch (Exception e1) {
			return true;
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		user = new userChecking();
		String username = NameTxt.getText(),
				email = EmailTxt.getText(),
				phone = PhoneTxt.getText(),
				address = addressTA.getText(),
				Gender = "",
				Id = user.Id;
		
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
					JOptionPane.showMessageDialog(null, "Address must consist 'street'");
			
			//gender
				else if(validGender(Gender))
					JOptionPane.showMessageDialog(null, "Gender must be choose !");
				
				else {
					int choose = JOptionPane.showOptionDialog(null, "Are you sure want to update profile?"
							, "Confirmation Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE
							, null, null, 0);
					if (choose == 0) {
					Gender = gender.getSelection().getActionCommand().toString();
					profile = new editProfileController();
					profile.update(username, email, phone, address, Gender, Id);
					
					JOptionPane.showMessageDialog(null, "Update Profile Successfully");
					}
				}
		
	}

}
