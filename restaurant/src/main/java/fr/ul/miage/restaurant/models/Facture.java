package fr.ul.miage.restaurant.Models;

public class Facture {

	private long id=0;
	private double montant;
	private String repas;
	
	public Facture() {
		super();
	}
	
	public Facture(long id, double montant, String repas) {
		super();
		this.id = id;
		this.montant = montant;
		this.repas = repas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getRepas() {
		return repas;
	}

	public void setRepas(String repas) {
		this.repas = repas;
	}
	
	
	
}
