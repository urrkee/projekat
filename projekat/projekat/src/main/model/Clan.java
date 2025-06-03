package main.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Clan extends Korisnik{
	private Clanarina clanarina;
	
	public Clan(String ime, String prezime, String email, String brTel, String lozinka, Clanarina clanarina) {
		super(ime, prezime, email, brTel,lozinka);
		this.clanarina = clanarina;
	}
	public Clan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Clan(String ime, String prezime, String email, String brTel,String lozinka) {
		super(ime, prezime, email, brTel, lozinka);
		// TODO Auto-generated constructor stub
	}
	public Clanarina getClanarina() {
		return clanarina;
	}
	public void setClanarina(Clanarina clanarina) {
		this.clanarina = clanarina;
	}
	@Override
	public boolean registrujSe() {
		
		if (emailPostoji(getEmail())) {
			return false;
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/clanovi.txt",true))){
			String direktor = getIme() + "|" + getPrezime() + "|" + getEmail() + "|" +getLozinka() + "|"+ getBrTel() +"|"+ getClanarina();
			writer.write(direktor);
			writer.newLine();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	private boolean emailPostoji(String email) {
		try(BufferedReader reader = new BufferedReader(new FileReader("src/data/clanovi.txt"))) {
			String clan;
			while ((clan = reader.readLine()) != null) {
				String[] clanAtributi = clan.split("\\|");
				String emailUFajlu = clanAtributi[2].trim();
				
				
				if(email.equals(emailUFajlu) ) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public static boolean logIn(String email, String lozinka) {
		try(BufferedReader reader = new BufferedReader(new FileReader("src/data/clanovi.txt"))) {
			String clan;
			while ((clan = reader.readLine()) != null) {
				String[] clanAtributi = clan.split("\\|");
				String emailUFajlu = clanAtributi[2].trim();
				String lozinkaUFajlu = clanAtributi[3].trim();
				
				if(email.equals(emailUFajlu) && lozinka.equals(lozinkaUFajlu)) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	
	@Override
	public String toString() {
		return "Clan [clanarina=" + clanarina + "]";
	}
	
	
	
	
	
	

}
