package fr.ul.miage.restaurant.models;

public class CompositionPlat {

	private long idProduit;
	private long idPlat;
	private int quantite;
	
	public CompositionPlat() {
		super();
	}
	
	public CompositionPlat(long idProduit, long idPlat, int quantite) {
		super();
		this.idPlat = idPlat;
		this.idProduit = idProduit;
		this.quantite = quantite;
	}

	public long getIdPlat() {
		return idPlat;
	}

	public void setIdPlat(long idPlat) {
		this.idPlat = idPlat;
	}

	public long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	
}
