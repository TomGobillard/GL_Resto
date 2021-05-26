package fr.ul.miage.restaurant.models;

public class Serveur extends Personnel {
	
	private long id=0;
	private int etage;
	
	public Serveur() {
		super();
	}

	public Serveur(long identifiant, String nom, String prenom, long id, int etage) {
		super(identifiant, nom, prenom);
		this.id = id;
		this.etage = etage;
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
