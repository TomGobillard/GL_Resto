package fr.ul.miage.restaurant.Models;

public class Personnel {
	private long identifiant = 0;
	private String role = "";
	
	public Personnel() {
		super();
	}

	public Personnel(long id, String role) {
		super();
		this.identifiant = id;
		this.role = role;
	}

	public long getId() {
		return identifiant;
	}

	public void setId(long id) {
		this.identifiant = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "id : " + this.identifiant + " | role : " + this.role; 
	}
	
	
}
