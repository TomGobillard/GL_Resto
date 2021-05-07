package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.models.Table;
import fr.ul.miage.restaurant.dao.TableDAO;

public class TableDAOImpl extends TableDAO {
	private Serveur serveur;

	public TableDAOImpl(Personnel serveur) {
		this.serveur = (Serveur) serveur;
	}
	
	public TableDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Table find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table create(Table obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table update(Table obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Table obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void obtenirInfoTable() {
		boolean reqFind = false;
		while (!reqFind) {
			System.out.println("Veuillez renseigner l'id de la table.");
			Scanner s = new Scanner(System.in);
			int choix = s.nextInt();
			String sql = "SELECT * FROM rtable WHERE idtable = ?";

			try {
				PreparedStatement stmt = connect.prepareStatement(sql);
				stmt.setLong(1, choix);

				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					reqFind = true;
					Table t = new Table(result.getLong(1), result.getLong(2), result.getLong(3), result.getString(4),
							result.getLong(5), result.getString(6), result.getLong(7));
					System.out.println(t);
				} else {
					System.out.println("Il n'y a pas de table enregistrée pour cet identifiant.");
				}
			} catch (Exception e) {

			}
		}
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
	
	public void assignServeur(long idServeur, long idTable) {
		String sql = "UPDATE rtable SET idserveur = ? WHERE idtable = ?";
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idServeur);
			stmt.setLong(2, idTable);
			stmt.executeQuery();
						
		} catch (Exception e) {
		}
	}
	
	public boolean tableExists(long idTable) {
		String sql = "SELECT * FROM rtable WHERE idtable = ?";
		boolean res = false;
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idTable);

			ResultSet result = stmt.executeQuery();
			res = result.next();
			
		} catch (Exception e) {
			res = false;
		}
		return res;
	}
	
	public void showAvancement(long idTable) {
		String sql = "SELECT * FROM rtable WHERE idtable = ?";
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idTable);

			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				String res = "Les clients de la table n°" + result.getInt("idtable") + " sont ";
				if(result.getString("avancement").equals("ENTREE")) {
					res += "à l'entrée";
				} else if(result.getString("avancement").equals("PLAT")) {
					res += "au plat";
				} else {
					res += "au dessert";
				}
				System.out.println(res);
			}
		} catch (Exception e) {
		
		}
	}
}
