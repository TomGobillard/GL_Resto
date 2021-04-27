package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import Models.Personnel;
import Models.Serveur;

public class ServeurDAOImpl extends ServeurDAO {
	
	private Serveur serveur;

	public ServeurDAOImpl(Personnel serveur) {
		this.serveur = (Serveur) serveur;
	}

	@Override
	public Serveur find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serveur create(Serveur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serveur update(Serveur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serveur obj) {
		// TODO Auto-generated method stub
		
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
	
	private HashMap<Integer, String> getOccupationTablesWithAvancement() {
		HashMap<Integer, String> occupations = new HashMap<Integer, String>();
		try {

			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT idtable, etat, avancement FROM rtable WHERE idserveur = \'" + this.serveur.getId() + "\' AND etage = \'" + this.serveur.getEtage() + "\'");
			while (result.next()) {
				if(result.getString("avancement") == null) {
					occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat"));
				} else {
					occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat") + " (" + result.getString("avancement") + ")");
				}
				
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
	
	public void printOccupationTablesWithAvancement() {
		System.out.println("Etat de vos tables : \n");
		HashMap<Integer, String> occupations = getOccupationTablesWithAvancement();
		for (Entry<Integer, String> entry : occupations.entrySet()) {
			System.out.println("Table n°" + entry.getKey() + " : " + entry.getValue());
		}
		System.out.println();
	}
}
