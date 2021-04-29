package fr.ul.miage.restaurant.Models;

public class Plat {

	private long id=0;
	private String libelle;
	private double prix;
	private boolean isPlatDuJour;
	private long nbCommandes;
	private long idcategorie;
	
	public Plat() {
		super();
	}
	
	public Plat(long id, String libelle, double prix, boolean isPlatDuJour, long nbCommandes, long idCategorie) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prix = prix;
		this.isPlatDuJour = isPlatDuJour;
		this.nbCommandes = nbCommandes;
		this.idcategorie = idCategorie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public boolean isPlatDuJour() {
		return isPlatDuJour;
	}

	public void setPlatDuJour(boolean isPlatDuJour) {
		this.isPlatDuJour = isPlatDuJour;
	}

	public long getNbCommandes() {
		return nbCommandes;
	}

	public void setNbCommandes(long nbCommandes) {
		this.nbCommandes = nbCommandes;
	}
	
	@Override
	public String toString() {
		return "Nom du plat : " + this.getLibelle() + "\n" +
				"Prix : " + this.getPrix() + "\n" +
				"Plat du jour ? " + this.isPlatDuJour() + "\n" +
				"Nombre de commandes : " + this.getNbCommandes() + "\n";
	}

	public long getIdcategorie() {
		return idcategorie;
	}

	public void setIdcategorie(long idcategorie) {
		this.idcategorie = idcategorie;
	}
	
}
