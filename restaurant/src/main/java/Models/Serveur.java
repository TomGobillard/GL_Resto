package Models;

public class Serveur extends Personnel{
	
	private long id=0;
	
	
	public Serveur() {
		super();
	}

	public Serveur(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

	
}
