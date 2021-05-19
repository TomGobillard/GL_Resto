package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		Table table = new Table();

		try {
			String sql = "SELECT * FROM rtable WHERE idTable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				table = new Table(id, result.getLong(2), result.getLong(3), result.getString(4), result.getLong(5), result.getString(6));
				table.setIdServeur(result.getLong(7));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return table;
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
			System.out.println("Veuillez renseignez le numéro de la table dont vous souhaitez obtenir les informations : ");
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
							result.getLong(5), result.getString(6), result.getLong(7),result.getLong(8));
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
			String sql = "SELECT idtable, etat FROM rtable WHERE idServeur = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, this.serveur.getId());

			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return occupations;
	}

	public HashMap<Integer, String> getTableForInitPrint() {
		HashMap<Integer, String> occupations = new HashMap<Integer, String>();
		try {

			String sql = "SELECT idtable, etat, avancement FROM rtable WHERE idServeur = ? AND etage = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, this.serveur.getId());
			stmt.setLong(2, this.serveur.getEtage());

			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				if (result.getString("avancement") == null) {
					occupations.put(Integer.valueOf(result.getInt("idtable")), result.getString("etat"));
				} else {
					occupations.put(Integer.valueOf(result.getInt("idtable")),
							result.getString("etat") + " (" + result.getString("avancement") + ")");
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
			if (result.next()) {
				String res = "Les clients de la table n°" + result.getInt("idtable") + " sont ";
				if (result.getString("avancement").equals("ENTREE")) {
					res += "à l'entrée.";
				} else if (result.getString("avancement").equals("PLAT")) {
					res += "au plat.";
				} else if (result.getString("avancement").equals("INSTALLE")) {
					res += "installés.";
				} else {
					res += "au dessert.";
				}
				System.out.println(res);
			}
		} catch (Exception e) {

		}
	}

	public ArrayList<Integer> getServeurTables(long serveurId) {
		ArrayList<Integer> tables = new ArrayList<>();
		try {

			String sql = "SELECT idtable FROM rtable WHERE idServeur = ? AND etat = 'OCCUPEE'";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, serveurId);

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				tables.add(Integer.valueOf(result.getInt("idtable")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}

	@Override
	public ArrayList<Table> getAll() {
		// TODO Auto-generated method stub
		ArrayList<Table> tables = new ArrayList<Table>();
		Table table;
		try {
			String sql = "SELECT * FROM rtable";
			PreparedStatement stmt = connect.prepareStatement(sql);
			
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				table = new Table(result.getLong(1), result.getLong(2), result.getLong(3), result.getString(4),
						result.getLong(5), result.getString(6), result.getLong(7),result.getLong(8));
				tables.add(table);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return tables;
	}

	@Override
	public ArrayList<Integer> getServeurTablesLibres(long serveurId) {
		ArrayList<Integer> tables = new ArrayList<>();
		try {

			String sql = "SELECT idtable FROM rtable WHERE idServeur = ? AND etat = 'PROPRE'";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, serveurId);

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				tables.add(Integer.valueOf(result.getInt("idtable")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}

	@Override
	public void installerClient(long idClient, long idTable) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE rtable SET idclient = ?, etat = 'OCCUPEE' WHERE idTable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idClient);
			stmt.setLong(2, idTable);
			
			ResultSet result = stmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void initTableTest(long idTable) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE rtable SET etat = 'PROPRE', idclient = null WHERE idtable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idTable);
			
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
