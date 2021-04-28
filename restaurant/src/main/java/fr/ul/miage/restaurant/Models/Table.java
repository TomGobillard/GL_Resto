package fr.ul.miage.restaurant.Models;

public class Table {

	private long id = 0;
	private long nbCouverts;
	private long occupation;
	private String etat;
	private long etage;
	private String avancement;
	private long idServeur;

	public Table() {
		super();
	}

	public Table(long id, long nbCouverts, long occupation, String etat, long etage, String avancement,
			long idServeur) {
		super();
		this.id = id;
		this.nbCouverts = nbCouverts;
		this.occupation = occupation;
		this.etat = etat;
		this.etage = etage;
		this.avancement = avancement;
		this.idServeur = idServeur;
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

	public long getOccupation() {
		return occupation;
	}

	public void setOccupation(long occupation) {
		this.occupation = occupation;
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
		String res = "Table n° " + this.id + "\n" + "Nombre de couverts : " + this.nbCouverts + "\n" + "Occupation : "
				+ this.occupation + "%" + "\n" + "Etat : " + this.etat + "\n" + "Etage : " + this.etage + "\n";
		
		if (this.etat.toUpperCase().equals("OCCUPEE")) {
			res += "Avancement du repas : " + this.avancement + "\n";
		}
		res += "N° de serveur associé : " + this.idServeur + "\n";
		return res;
	}

}
