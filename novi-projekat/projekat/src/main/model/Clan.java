package main.model;

import main.controller.ClanController;

public class Clan extends Korisnik {
    private Clanarina clanarina;

    public Clan(String ime, String prezime, String email, String brTel, String lozinka, Clanarina clanarina) {
        super(ime, prezime, email, brTel, lozinka);
        this.clanarina = clanarina;
    }

    public Clan() {
        super();
    }

    public Clan(String ime, String prezime, String email, String brTel, String lozinka) {
        super(ime, prezime, email, brTel, lozinka);
    }

    public Clanarina getClanarina() {
        return clanarina;
    }

    public void setClanarina(Clanarina clanarina) {
        this.clanarina = clanarina;
    }

    @Override
    public boolean registrujSe() {
        // Poziva se iz kontrolera, pa ne treba implementacija ovde
    	 return ClanController.registrujSe(this);
    }

    @Override
    public String toString() {
        return "Clan [clanarina=" + clanarina + "]";
    }
}
