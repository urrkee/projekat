package main.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import main.controller.ClanController;

public class LoginPanel extends JPanel{
	private static final long serialVersionUID = -7647074379910287568L;
	
	protected JLabel emailLabel = new JLabel("Email:");
	protected JLabel lozinkaLabel = new JLabel("Lozinka:");
	protected JTextField emailInput = new JTextField(20);
	protected JPasswordField lozinkaInput = new JPasswordField(20);
	
	protected SpringLayout springLayout = new SpringLayout();
	protected JButton logInButton = new JButton("Log In");
	
	public LoginPanel() {
		this.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, emailLabel, 100, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,emailInput, 10, SpringLayout.NORTH,this);
		springLayout.putConstraint(SpringLayout.WEST, emailInput,20, SpringLayout.EAST,emailLabel);
		springLayout.putConstraint(SpringLayout.EAST, emailInput, -20, SpringLayout.EAST,this);
		
		springLayout.putConstraint(SpringLayout.NORTH, lozinkaLabel, 15, SpringLayout.SOUTH, emailLabel);
		springLayout.putConstraint(SpringLayout.EAST, lozinkaLabel, 100, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,lozinkaInput, 10, SpringLayout.SOUTH,emailInput);
		springLayout.putConstraint(SpringLayout.WEST, lozinkaInput,20, SpringLayout.EAST,lozinkaLabel);
		springLayout.putConstraint(SpringLayout.EAST, lozinkaInput, -20, SpringLayout.EAST,this);
	
		springLayout.putConstraint(SpringLayout.NORTH, logInButton, 50, SpringLayout.SOUTH, lozinkaLabel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logInButton, 0, SpringLayout.HORIZONTAL_CENTER,this);
		
		this.add(emailLabel);
		this.add(emailInput);
		this.add(lozinkaLabel);
		this.add(lozinkaInput);
		this.add(logInButton);
		
		logInButton.addActionListener(e ->{
			String email = emailInput.getText().trim();
			String lozinka = new String(lozinkaInput.getPassword());
			
			boolean logIn = ClanController.login(email, lozinka);
			
			if (logIn) {
				JOptionPane.showMessageDialog(this, "Uspesna Prijava");
			}else {
				JOptionPane.showMessageDialog(this, "Neispravan email ili lozinka");
			}
		});
	
	}
}
