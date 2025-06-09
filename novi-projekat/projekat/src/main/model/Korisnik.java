package main.model;

public abstract class Korisnik {
	private String ime;
	private String prezime;
	private String email;
	private String brTel;
	private String lozinka;
	
	public Korisnik(String ime, String prezime, String email, String brTel, String lozinka) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.brTel = brTel;
		this.lozinka = lozinka;
	}

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrTel() {
		return brTel;
	}

	public void setBrTel(String brTel) {
		this.brTel = brTel;
	}
	
	
	
	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	

	@Override
	public String toString() {
		return "Korisnik [ime=" + ime + ", prezime=" + prezime + ", email=" + email + ", brTel=" + brTel + ", lozinka="
				+ lozinka + "]";
	}

	public abstract boolean registrujSe();
}
