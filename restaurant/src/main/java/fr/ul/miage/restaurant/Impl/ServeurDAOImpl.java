package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.dao.ServeurDAO;

public class ServeurDAOImpl extends ServeurDAO {

	private Serveur serveur;

	public ServeurDAOImpl(Personnel serveur) {
		this.serveur = (Serveur) serveur;
	}
	
	public ServeurDAOImpl() {
		this.serveur = null;
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

	public ArrayList<Integer> get() {
		ArrayList<Integer> tables = new ArrayList<>();
		try {

			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT idtable FROM rtable WHERE idserveur = \'" + this.serveur.getId() + "\'");
			while (result.next()) {
				tables.add(Integer.valueOf(result.getInt("idtable")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
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
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT idtable, etat, avancement FROM rtable WHERE idserveur = \'"
							+ this.serveur.getId() + "\' AND etage = \'" + this.serveur.getEtage() + "\'");
			while (result.next()) {
				if (result.getString("avancement") == null) {
					occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat"));
				} else {
					occupations.put(Integer.valueOf(result.getInt("idtable")),
							result.getString("etat") + " (" + result.getString("avancement") + ")");
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
	
	public boolean serveurExists(long idServeur) {
		String sql = "SELECT * FROM SERVEUR WHERE idserveur = ?";
		boolean res = false;
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idServeur);

			ResultSet result = stmt.executeQuery();
			res = result.next();
			
		} catch (Exception e) {
			res = false;
		}
		return res;
	}

}
