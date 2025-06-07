package main.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import main.controller.ClanController;
import main.model.Clan;
import main.model.Clanarina;

public class RegisterPanel extends JPanel{
	private static final long serialVersionUID = -6242684725486513987L;
	
	protected JLabel imeLabel = new JLabel("Ime:");
	protected JLabel prezimeLabel = new JLabel("Prezime:");
	protected JLabel emailLabel = new JLabel("Email:");
	protected JLabel brTelLabel = new JLabel("Telefon:");
	protected JLabel lozinkaLabel = new JLabel("Lozinka:");
	protected JTextField imeInput = new JTextField(20);
	protected JTextField prezimeInput = new JTextField(20);
	protected JTextField emailInput = new JTextField(20);
	protected JTextField brTelInput = new JTextField(20);
	protected JPasswordField lozinkaInput = new JPasswordField(20);
	protected SpringLayout springLayout = new SpringLayout();
	protected JButton registerButton = new JButton("Registruj Se");
	protected int selektovanRed = -1;
	
	protected JScrollPane scrollPane;
	
	public RegisterPanel() {
		
		this.setLayout(springLayout);
		
		springLayout.putConstraint(SpringLayout.NORTH, imeLabel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, imeLabel, 100, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,imeInput, 10, SpringLayout.NORTH,this);
		springLayout.putConstraint(SpringLayout.WEST, imeInput,20, SpringLayout.EAST,imeLabel);
		springLayout.putConstraint(SpringLayout.EAST, imeInput, -20, SpringLayout.EAST,this);
		
		springLayout.putConstraint(SpringLayout.NORTH, prezimeLabel, 15, SpringLayout.SOUTH, imeLabel);
		springLayout.putConstraint(SpringLayout.EAST, prezimeLabel, 100, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,prezimeInput, 10, SpringLayout.SOUTH,imeInput);
		springLayout.putConstraint(SpringLayout.WEST, prezimeInput,20, SpringLayout.EAST,prezimeLabel);
		springLayout.putConstraint(SpringLayout.EAST, prezimeInput, -20, SpringLayout.EAST,this);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 15, SpringLayout.SOUTH, prezimeLabel);
		springLayout.putConstraint(SpringLayout.EAST, emailLabel, 100, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,emailInput, 10, SpringLayout.SOUTH,prezimeInput);
		springLayout.putConstraint(SpringLayout.WEST, emailInput,20, SpringLayout.EAST,emailLabel);
		springLayout.putConstraint(SpringLayout.EAST, emailInput, -20, SpringLayout.EAST,this);
		
		springLayout.putConstraint(SpringLayout.NORTH, lozinkaLabel, 15, SpringLayout.SOUTH, emailLabel);
		springLayout.putConstraint(SpringLayout.EAST, lozinkaLabel, 100, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,lozinkaInput, 10, SpringLayout.SOUTH,emailInput);
		springLayout.putConstraint(SpringLayout.WEST, lozinkaInput,20, SpringLayout.EAST,lozinkaLabel);
		springLayout.putConstraint(SpringLayout.EAST, lozinkaInput, -20, SpringLayout.EAST,this);
		
		springLayout.putConstraint(SpringLayout.NORTH, brTelLabel, 15, SpringLayout.SOUTH, lozinkaLabel);
		springLayout.putConstraint(SpringLayout.EAST, brTelLabel, 100, SpringLayout.WEST,this);
		springLayout.putConstraint(SpringLayout.NORTH,brTelInput, 10, SpringLayout.SOUTH,lozinkaInput);
		springLayout.putConstraint(SpringLayout.WEST, brTelInput,20, SpringLayout.EAST,brTelLabel);
		springLayout.putConstraint(SpringLayout.EAST, brTelInput, -20, SpringLayout.EAST,this);
		
		springLayout.putConstraint(SpringLayout.NORTH, registerButton, 50, SpringLayout.SOUTH, brTelLabel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, registerButton, 0, SpringLayout.HORIZONTAL_CENTER,this);
		
		this.add(imeLabel);
		this.add(imeInput);
		this.add(prezimeLabel);
		this.add(prezimeInput);
		this.add(emailLabel);
		this.add(emailInput);
		this.add(brTelLabel);
		this.add(brTelInput);
		this.add(lozinkaLabel);
		this.add(lozinkaInput);
		this.add(registerButton);
		
		registerButton.addActionListener(e->{
			
			String ime = imeInput.getText().trim();
			String prezime = prezimeInput.getText().trim();
			String email = emailInput.getText().trim();
			String lozinka = new String(lozinkaInput.getPassword()).trim();
			String brTel = brTelInput.getText().trim();
		 
			if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || lozinka.isEmpty() || brTel.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena!", "Greska", JOptionPane.WARNING_MESSAGE);
				return;
			}
		 
			if (!email.contains("@")) {
				JOptionPane.showMessageDialog(this, "Unesite ispravan email!", "Greska", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			Clan noviClan = getPodaci();
			
			boolean register = ClanController.registracija(noviClan);
			
			if(register) {
				JOptionPane.showMessageDialog(this, "Uspesno ste se registrovali");
				
				imeInput.setText("");
				prezimeInput.setText("");
				emailInput.setText("");
				lozinkaInput.setText("");
				brTelInput.setText("");
			}else {
				JOptionPane.showMessageDialog(this, "Email vec Postoji");
			}
			
		});
		
	}
	
	
	
	
	public Clan getPodaci() {
		String ime = imeInput.getText().trim();
		String prezime = prezimeInput.getText().trim();
		String email = emailInput.getText().trim();
		String lozinka = new String(lozinkaInput.getPassword()).trim();;
		String brTel = brTelInput.getText().trim();
		Clanarina clanarina = new Clanarina();
		System.out.println(clanarina);
		return new Clan(ime, prezime, email,brTel,lozinka,clanarina);
	}
	
}
