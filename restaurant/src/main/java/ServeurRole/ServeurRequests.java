package ServeurRole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Personnel;
import Models.Serveur;
import database.ConnectionPostgresSQL;

public class ServeurRequests {
	private Connection connect = ConnectionPostgresSQL.getInstance();
	private Serveur serveur;
	
	public ServeurRequests(Personnel serveurParam) {
		this.serveur = (Serveur) serveurParam;
	}
	
	private HashMap<Integer, String> getOccupationTables() {
		HashMap<Integer, String> occupations = new HashMap<Integer, String>();
		try {
			
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT idtable, etat FROM rtable WHERE idserveur = \'" + this.serveur.getId() + "\'");
			System.out.println("SELECT idtable, etat FROM rtable WHERE idserveur = \'" + this.serveur.getId() + "\'");
				while(result.next()) {
					occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat"));
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return occupations;
	}
	
	public void printOccupationTables() {
		HashMap<Integer, String> occupations = getOccupationTables();
		for (Entry<Integer, String> entry : occupations.entrySet()) {
			System.out.println("Table n°" + entry.getKey() + " : " + entry.getValue());
		}
		
	}

}
