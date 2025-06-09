package main.view;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import main.model.Clan;

public class UserInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private JLabel imeLabel;
    private JLabel prezimeLabel;
    private JLabel qrLabel;
    private JLabel qrTitleLabel;
    private JButton logoutButton;
    
    public UserInfoPanel(Clan clan) {
        initializeComponents(clan);
        layoutComponents();
        addLogoutFunctionality();
    }
    
    private void initializeComponents(Clan clan) {
        // Kreiranje komponenti za prikaz informacija
        imeLabel = new JLabel("Ime: " + clan.getIme(), JLabel.CENTER);
        prezimeLabel = new JLabel("Prezime: " + clan.getPrezime(), JLabel.CENTER);
        
        // Naslov za QR kod sekciju
        qrTitleLabel = new JLabel("QR Kod Članarine", JLabel.CENTER);
        
        // Kreiranje QR koda slike
        BufferedImage qrImage = clan.getClanarina().getQRImage();
        if (qrImage != null) {
            ImageIcon qrIcon = new ImageIcon(qrImage);
            qrLabel = new JLabel(qrIcon, JLabel.CENTER);
        } else {
            qrLabel = new JLabel("QR kod nije dostupan", JLabel.CENTER);
        }
        
        // Dugme za odjavu
        logoutButton = new JButton("Odjavi se");
        logoutButton.setPreferredSize(new Dimension(100, 30));
    }
    
    private void layoutComponents() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        
        // Dodavanje komponenti u panel
        add(imeLabel);
        add(prezimeLabel);
        add(qrTitleLabel);
        add(qrLabel);
        add(logoutButton);
        
        // Podešavanje pozicija komponenti
        
        // Ime label - pozicioniran na vrhu panela sa marginom
        layout.putConstraint(SpringLayout.NORTH, imeLabel, 20, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, imeLabel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, imeLabel, -20, SpringLayout.EAST, this);
        
        // Prezime label - ispod ime labela
        layout.putConstraint(SpringLayout.NORTH, prezimeLabel, 10, SpringLayout.SOUTH, imeLabel);
        layout.putConstraint(SpringLayout.WEST, prezimeLabel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, prezimeLabel, -20, SpringLayout.EAST, this);
        
        // QR naslov - ispod prezime labela sa dodatnom marginom
        layout.putConstraint(SpringLayout.NORTH, qrTitleLabel, 30, SpringLayout.SOUTH, prezimeLabel);
        layout.putConstraint(SpringLayout.WEST, qrTitleLabel, 20, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, qrTitleLabel, -20, SpringLayout.EAST, this);
        
        // QR label - centriran ispod naslova
        layout.putConstraint(SpringLayout.NORTH, qrLabel, 15, SpringLayout.SOUTH, qrTitleLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, qrLabel, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        // Logout dugme - ispod QR labela sa marginom
        layout.putConstraint(SpringLayout.NORTH, logoutButton, 30, SpringLayout.SOUTH, qrLabel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logoutButton, 0, SpringLayout.HORIZONTAL_CENTER, this);
        
        // Postavljanje minimalne veličine panela
        setPreferredSize(new Dimension(400, 500));
    }
    
    private void addLogoutFunctionality() {
        logoutButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                this,
                "Da li ste sigurni da se želite odjaviti?",
                "Potvrda odjave",
                JOptionPane.YES_NO_OPTION
            );
            
            if (response == JOptionPane.YES_OPTION) {
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window instanceof JFrame) {
                    ((JFrame) window).dispose();
                }
            }
        });
    }
}