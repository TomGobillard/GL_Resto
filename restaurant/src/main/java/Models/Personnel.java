package Models;

public class Personnel {
	private long id = 0;
	private String role = "";
	
	public Personnel() {
		super();
	}

	public Personnel(long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "id : " + this.id + " | role : " + this.role; 
	}
	
	
}
