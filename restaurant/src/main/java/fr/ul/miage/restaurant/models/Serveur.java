package fr.ul.miage.restaurant.Models;

public class Serveur extends Personnel {
	
	private long id=0;
	private int etage;
	
	public Serveur() {
		super();
	}

	public Serveur(long id, int etageParam) {
		super(id, "SERVEUR");
		this.id = id;
		this.etage = etageParam;
	}
	
	public int getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
}