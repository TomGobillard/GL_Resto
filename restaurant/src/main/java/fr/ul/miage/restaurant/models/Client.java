package fr.ul.miage.restaurant.Models;

public class Client {

	private long id=0;
	private String heureDepart;
	private String heureArrivee;
	
	public Client() {
		super();
	}
	
	public Client(long id, String heureDepart, String heureArrivee) {
		super();
		this.id = id;
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
	}

	public String getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(String heureArrivee) {
		this.heureArrivee = heureArrivee;
	}
	
	
	
}
