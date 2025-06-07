package main.model;



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
	public String toString() {
		return "Clan [clanarina=" + clanarina + "]";
	}
	
	
	
	
	
	

}
