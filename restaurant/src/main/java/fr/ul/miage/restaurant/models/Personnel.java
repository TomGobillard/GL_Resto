package fr.ul.miage.restaurant.models;

public class Personnel {
	private long identifiant = 0;
	private String role = "";
	private String login;
	private String mdp;
	private String nom;
	private String prenom;
	
	public Personnel(long identifiant, String role, String login, String mdp, String nom, String prenom) {
		super();
		this.identifiant = identifiant;
		this.role = role;
		this.login = login;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Personnel(String role, String login, String mdp, String nom, String prenom) {
		super();
		this.role = role;
		this.login = login;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Personnel(long identifiant, String nom, String prenom) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
	}

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
	
	public long getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(long identifiant) {
		this.identifiant = identifiant;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Nom : " + this.nom + " " + this.prenom + "\n" + "Identifiant : " + this.identifiant + "\nRôle : " + this.role + "\n";
	}
}
