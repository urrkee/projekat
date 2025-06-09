package main.view;

import javax.swing.*;
import main.controller.*;
import main.model.Clan;

public class LoginPanel extends JPanel {
    private static final long serialVersionUID = -7647074379910287568L;
    
    protected JLabel emailLabel = new JLabel("Email:");
    protected JLabel lozinkaLabel = new JLabel("Lozinka:");
    protected JTextField emailInput = new JTextField(20);
    protected JPasswordField lozinkaInput = new JPasswordField(20);
    
    protected SpringLayout springLayout = new SpringLayout();
    protected JButton logInButton = new JButton("Log In");
    
    // Metoda za dobijanje Clan objekta na osnovu email-a
    private Clan getClanByEmail(String email) {
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("src/data/clanovi.txt"))) {
            String clan;
            while ((clan = reader.readLine()) != null) {
                String[] clanAtributi = clan.split("\\|");
                String emailUFajlu = clanAtributi[2].trim();
                
                if (email.equals(emailUFajlu)) {
                    String ime = clanAtributi[0].trim();
                    String prezime = clanAtributi[1].trim();
                    String lozinka = clanAtributi[3].trim();
                    String brTel = clanAtributi[4].trim();
                    
                    // Kreiranje Clan objekta
                    Clan clanObj = new Clan(ime, prezime, email, brTel, lozinka);
                    
                    // Kreiranje nove clanarine jer se u fajlu čuva toString reprezentacija
                    main.model.Clanarina clanarina = new main.model.Clanarina(email);
                    clanObj.setClanarina(clanarina);
                    
                    return clanObj;
                }
            }
        } catch (Exception e) {
            System.err.println("Greška pri čitanju fajla: " + e.getMessage());
        }
        return null;
    }
    
    public LoginPanel() {
        this.setLayout(springLayout);
        
        springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, emailLabel, 100, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, emailInput, 10, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, emailInput, 20, SpringLayout.EAST, emailLabel);
        springLayout.putConstraint(SpringLayout.EAST, emailInput, -20, SpringLayout.EAST, this);
        
        springLayout.putConstraint(SpringLayout.NORTH, lozinkaLabel, 15, SpringLayout.SOUTH, emailLabel);
        springLayout.putConstraint(SpringLayout.EAST, lozinkaLabel, 100, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, lozinkaInput, 10, SpringLayout.SOUTH, emailInput);
        springLayout.putConstraint(SpringLayout.WEST, lozinkaInput, 20, SpringLayout.EAST, lozinkaLabel);
        springLayout.putConstraint(SpringLayout.EAST, lozinkaInput, -20, SpringLayout.EAST, this);
    
        springLayout.putConstraint(SpringLayout.NORTH, logInButton, 50, SpringLayout.SOUTH, lozinkaLabel);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logInButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        this.add(emailLabel);
        this.add(emailInput);
        this.add(lozinkaLabel);
        this.add(lozinkaInput);
        this.add(logInButton);
        
        logInButton.addActionListener(e -> {
            String email = emailInput.getText().trim();
            String lozinka = new String(lozinkaInput.getPassword());
            
            boolean logIn = ClanController.login(email, lozinka);
            
            if (logIn) {
                // Trebam da dobijem Clan objekat na osnovu email-a
                Clan clan = getClanByEmail(email);
               
                
                if (clan != null) {
                    // Kreiranje novog UserInfoPanel-a
                    UserInfoPanel userInfoPanel = new UserInfoPanel(clan);
                    
                    // Dobijanje roditeljskog frame-a
                    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    
                    if (parentFrame != null) {
                        // Zamena sadrzaj frame-a
                        parentFrame.getContentPane().removeAll(); //prvo brisem sve sa trenutnog prozora
                        parentFrame.getContentPane().add(userInfoPanel);//dodajem panel sa podacima usera
                        parentFrame.revalidate();//osvezavam GUI
                        parentFrame.repaint(); //osvezavam GUI (ponovo iscrtavam prozor)
                 
                        
                        parentFrame.pack(); // podesavam velicinu prozora
                        parentFrame.setLocationRelativeTo(null); // centriram prozor
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Greška pri učitavanju podataka člana");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Neispravan email ili lozinka");
            }
        });
    }
}