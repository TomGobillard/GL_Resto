package Models;

public class Produit {

	private long id=0;
	private String libelle;
	private int quantite;
	
	public Produit() {
		super();
	}
	
	
	public Produit(long id, String libelle, int quantite) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.quantite = quantite;
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
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
}
