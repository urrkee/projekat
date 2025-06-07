package main.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.model.Clan;

public class ClanController {
	public static boolean registracija(Clan noviClan) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/clanovi.txt",true))){
			String clan = noviClan.getIme() + "|" + noviClan.getPrezime() + "|" +noviClan.getEmail() + "|" + noviClan.getLozinka() + "|" + noviClan.getBrTel() + "|" + noviClan.getClanarina();
			writer.write(clan);
			writer.newLine();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
}

public static boolean login(String email, String lozinka) {
	try (BufferedReader reader = new BufferedReader(new FileReader("src/data/clanovi.txt"))){
		String clan;
		while ((clan = reader.readLine()) != null) {
			String[] clanAtributi = clan.split("\\|");
			String eamilUFajlu = clanAtributi[2].trim();
			String lozinkaUfajlu = clanAtributi[3].trim();
			
			if (email.equals(eamilUFajlu) && lozinka.equals(lozinkaUfajlu)) {
				return true;
			}
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	
	}

	public static boolean emailPostoji(String email) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/data/clanovi.txt"))){
			String clan;
			while ((clan = reader.readLine()) != null) {
				String[] clanAtributi = clan.split("\\|");
				String emailUFajlu = clanAtributi[2];
				
				if (email.equals(emailUFajlu)) {
					return true;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
