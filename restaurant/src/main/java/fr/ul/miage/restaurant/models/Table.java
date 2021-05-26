package fr.ul.miage.restaurant.models;

public class Table {

	private long id = 0;
	private long nbCouverts;
	private String etat;
	private long etage;
	public String getAvancement() {
		return avancement;
	}

	public void setAvancement(String avancement) {
		this.avancement = avancement;
	}

	private String avancement;
	private long idServeur;
	private long idClient;


	public Table() {
		super();
	}

	public Table(long id, long nbCouverts, String etat, long etage, String avancement, long idServeur, long idClient) {
		super();
		this.id = id;
		this.nbCouverts = nbCouverts;
		this.etat = etat;
		this.etage = etage;
		this.avancement = avancement;
		this.idServeur = idServeur;
		this.idClient = idClient;
	}
	


	public Table(long id, long nbCouverts, String etat, long etage, String avancement) {
		super();
		this.id = id;
		this.nbCouverts = nbCouverts;
		this.etat = etat;
		this.etage = etage;
		this.avancement = avancement;
		this.idServeur = -1;
		this.idClient = -1;
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

	public long getNbCouverts() {
		return nbCouverts;
	}

	public void setNbCouverts(int nbCouverts) {
		this.nbCouverts = nbCouverts;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public long getEtage() {
		return etage;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	public long getIdServeur() {
		return idServeur;
	}

	public void setIdServeur(long idServeur) {
		this.idServeur = idServeur;
	}

	@Override
	public String toString() {
		String res = "Table n°" + this.id + "\n" + "Nombre de couverts : " + this.nbCouverts + "\n" + "Etat : " + this.etat + "\n" + "Etage : " + this.etage + "\n";
		
		if (this.etat.toUpperCase().equals("OCCUPEE")) {
			res += "Avancement du repas : " + this.avancement + "\n";
		}
		res += "Numéro du serveur associé : " + this.idServeur + "\n";
		return res;
	}

}
