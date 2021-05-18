package fr.ul.miage.restaurant.models;

public class Commande {
	
	private long id=0;
	private String heure_cmd_prete;
	private String heure_cmd_passee;
	private String etat;
	private long idClient;
	private long idTable; 
	
	public Commande() {
		super();
	}
	
	
	public Commande(long id, String heure_cmd_prete, String heure_cmd_passee, String etat, long idClient, long idTable) {
		super();
		this.id = id;
		this.heure_cmd_prete = heure_cmd_prete;
		this.heure_cmd_passee = heure_cmd_passee;
		this.etat = etat;
		this.idClient = idClient;
		this.idTable = idTable;
	}
	
	
	public long getIdTable() {
		return idTable;
	}


	public void setIdTable(long idTable) {
		this.idTable = idTable;
	}


	public long getIdClient() {
		return idClient;
	}


	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}


	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getHeure_cmd_prete() {
		return heure_cmd_prete;
	}
	
	public void setHeure_cmd_prete(String heure_cmd_prete) {
		this.heure_cmd_prete = heure_cmd_prete;
	}
	
	public String getHeure_cmd_passee() {
		return heure_cmd_passee;
	}
	
	public void setHeure_cmd_passee(String heure_cmd_passee) {
		this.heure_cmd_passee = heure_cmd_passee;
	}
	
	public String getEtat() {
		return etat;
	}
	
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	

}
