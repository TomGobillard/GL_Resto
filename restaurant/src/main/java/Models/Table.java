package Models;

public class Table {
	
	private long id = 0;
	private int nbCouverts;
	private String occupation;
	private String etat;
	private int etage;
	private long idServeur;
	
	public Table() {
		super();
	}
	
	public Table(long id, int nbCouverts, String occupation, String etat, int etage, long idServeur) {
		super();
		this.id = id;
		this.nbCouverts = nbCouverts;
		this.occupation = occupation;
		this.etat = etat;
		this.etage = etage;
		this.idServeur = idServeur;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNbCouverts() {
		return nbCouverts;
	}

	public void setNbCouverts(int nbCouverts) {
		this.nbCouverts = nbCouverts;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getEtage() {
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
	
	
	
	

}
