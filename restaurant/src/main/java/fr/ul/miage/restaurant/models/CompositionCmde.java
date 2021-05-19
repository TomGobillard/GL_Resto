
package fr.ul.miage.restaurant.models;

public class CompositionCmde {
	
	private long idCommande;
	private long idPlat;
	private String etat;
	private int quantite;
	
	
	public CompositionCmde(long idCommande, long idPlat, String etat, int quantite) {
		super();
		this.idCommande = idCommande;
		this.idPlat = idPlat;
		this.etat = etat;
		this.quantite = quantite;
	}
	
	public CompositionCmde(long idCommande, long idPlat) {
		super();
		this.idCommande = idCommande;
		this.idPlat = idPlat;

	}
	
	
	public long getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}
	public long getIdPlat() {
		return idPlat;
	}
	public void setIdPlat(long idPlat) {
		this.idPlat = idPlat;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
