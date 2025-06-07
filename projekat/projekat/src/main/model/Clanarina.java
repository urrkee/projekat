package main.model;

import java.time.LocalDate;

public class Clanarina {
	private LocalDate datumPocetka;
	private LocalDate datumIsteka;
	
	
	
	public Clanarina() {
		super();
		this.datumPocetka = LocalDate.now();
		this.datumIsteka = datumPocetka.plusMonths(1);
	}

	


	public LocalDate getDatumPocetka() {
		return datumPocetka;
	}




	public void setDatumPocetka(LocalDate datumPocetka) {
		this.datumPocetka = datumPocetka;
	}




	public LocalDate getDatumIsteka() {
		return datumIsteka;
	}




	public void setDatumIsteka(LocalDate datumIsteka) {
		this.datumIsteka = datumIsteka;
	}




	




	@Override
	public String toString() {
		return "Clanarina [datumPocetka=" + datumPocetka + ", datumIsteka=" + datumIsteka + "]";
	}
	
	
}
