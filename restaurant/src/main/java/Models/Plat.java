package Models;

public class Plat {

	private long id=0;
	private String libelle;
	private double prix;
	private boolean isPlatDuJour;
	private int nbCommandes;
	
	public Plat() {
		super();
	}
	
	public Plat(long id, String libelle, double prix, boolean isPlatDuJour, int nbCommandes) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prix = prix;
		this.isPlatDuJour = isPlatDuJour;
		this.nbCommandes = nbCommandes;
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

	public int getNbCommandes() {
		return nbCommandes;
	}

	public void setNbCommandes(int nbCommandes) {
		this.nbCommandes = nbCommandes;
	}
	
	
}
