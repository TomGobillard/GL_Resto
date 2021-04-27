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

	private HashMap<Integer, String> getOccupationAllTables() {
		HashMap<Integer, String> occupations = new HashMap<Integer, String>();
		try {

			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT idtable, etat FROM rtable WHERE idserveur = \'" + this.serveur.getId() + "\'");
			while (result.next()) {
				occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return occupations;
	}
	
	private HashMap<Integer, String> getOccupationTablesWithServeurEtage() {
		HashMap<Integer, String> occupations = new HashMap<Integer, String>();
		try {

			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT idtable, etat FROM rtable WHERE idserveur = \'" + this.serveur.getId() + "\' AND etage = \'" + this.serveur.getEtage() + "\'");
			while (result.next()) {
				occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return occupations;
	}

	public void printOccupationAllTables() {
		System.out.println("Etat de toutes les tables : \n");
		HashMap<Integer, String> occupations = getOccupationAllTables();
		for (Entry<Integer, String> entry : occupations.entrySet()) {
			System.out.println("Table n°" + entry.getKey() + " : " + entry.getValue());
		}
		System.out.println();

	}
	
	public void printOccupationTablesWithServeurEtage() {
		System.out.println("Etat de vos tables : \n");
		HashMap<Integer, String> occupations = getOccupationTablesWithServeurEtage();
		for (Entry<Integer, String> entry : occupations.entrySet()) {
			System.out.println("Table n°" + entry.getKey() + " : " + entry.getValue());
		}
		System.out.println();

	}

}
