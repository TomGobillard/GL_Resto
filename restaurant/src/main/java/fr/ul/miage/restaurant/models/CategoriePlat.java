package fr.ul.miage.restaurant.models;

public class CategoriePlat {
	private long id=0;
	private String libelle;
	
	public CategoriePlat() {
		super();
	}
	
	public CategoriePlat(long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
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
	
	
}
