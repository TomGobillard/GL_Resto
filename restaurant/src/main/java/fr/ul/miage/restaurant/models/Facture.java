package fr.ul.miage.restaurant.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Facture {

	private long id=0;
	private double montant;
	private String repas;
	private long idClient;
	private Date date;
	
	public Facture() {
		super();
	}
	
	public Facture(long id, double montant, String repas, long idClient, Date date) {
		super();
		this.id = id;
		this.montant = montant;
		this.repas = repas;
		this.idClient = idClient;
		this.date = date;
		
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	
	@Override
	public String toString() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		return "Facture n°" + this.id + "\n" + "Type de repas : " + this.repas + "\n" + "Editée le " + formatter.format(this.date) + "\n" + "Client n°" + this.idClient + "\n" + "Montant facturé : " + this.montant + "€";
	}
	
	
}
