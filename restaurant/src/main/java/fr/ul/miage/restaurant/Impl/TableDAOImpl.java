package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.models.Table;

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
			String sql = "SELECT * FROM rtable WHERE idtable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				table = new Table(id, result.getInt(2),result.getString(3), result.getInt(4), result.getString(5), result.getInt(6), result.getInt(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
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
	public Table obtenirInfoTable(long idTable) {
			String sql = "SELECT * FROM rtable WHERE idtable = ?";
			Table t = new Table();
			try {
				PreparedStatement stmt = connect.prepareStatement(sql);
				stmt.setLong(1, idTable);

				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					t = new Table(result.getLong(1), result.getLong(2),result.getString(3), result.getLong(4), result.getString(5), result.getLong(6),result.getLong(7));
				} else {
					System.out.println("Il n'y a pas de table enregistrée pour cet identifiant.");
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			return t;

	}


	public ArrayList<Table> getOccupationAllTables() {
		ArrayList<Table> tables = new ArrayList<Table>();
		try {
			String sql = "SELECT idtable, etat FROM rtable WHERE idServeur = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, this.serveur.getId());

			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				Table t = new Table(result.getLong(1), result.getLong(2),result.getString(3), result.getLong(4), result.getString(5), result.getLong(6),result.getLong(7));
				tables.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
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

	public void assignServeur(long idServeur, long idTable) {
		String sql = "UPDATE rtable SET idserveur = ? WHERE idtable = ?";
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idServeur);
			stmt.setLong(2, idTable);

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return res;
	}

	public String showAvancement(long idTable) {
		String sql = "SELECT * FROM rtable WHERE idtable = ?";
		String res = "";
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idTable);

			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				res = "Les clients de la table n°" + result.getInt("idtable") + " sont ";
				if (result.getString("avancement").equals("VIDE")) {
					res = "La table est vide.";
				} else if (result.getString("avancement").equals("EN REPAS")) {
					res += "en train de manger.";
				} else if (result.getString("avancement").equals("INSTALLE")) {
					res += "installés.";
				} else {
					res += "partis.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public ArrayList<Integer> getServeurTablesId(long serveurId) {
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
	
	public ArrayList<Table> getServeurTables(long serveurId) {
		ArrayList<Table> tables = new ArrayList<Table>();
		try {

			String sql = "SELECT * FROM rtable WHERE idServeur = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, serveurId);

			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				Table table = new Table(result.getLong(1), result.getLong(2),result.getString(3), result.getLong(4), result.getString(5), result.getLong(6),result.getLong(7));
				tables.add(table);
			}

		} catch (SQLException e) {
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
				table = new Table(result.getLong(1), result.getLong(2),result.getString(3), result.getLong(4), result.getString(5), result.getLong(6),result.getLong(7));
				tables.add(table);
			}
		}catch (Exception e) {
			e.printStackTrace();
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
		try {
			String sql = "UPDATE rtable SET avancement = 'INSTALLE', idclient = ?, etat = 'OCCUPEE' WHERE idTable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idClient);
			stmt.setLong(2, idTable);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initTableTest(long idTable) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE rtable SET etat = 'PROPRE', idclient = null WHERE idtable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idTable);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Table> getTableRepasFini() {
		ArrayList<Table> listTables = new ArrayList<Table>();
		try {
			
			String sql = "SELECT * FROM rtable WHERE avancement = 'EN REPAS' AND etat = 'OCCUPEE'";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			while(result.next()) {
				listTables.add(new Table(result.getLong(1), result.getLong(2), result.getString(3), result.getInt(4), result.getString(5), result.getLong(6), result.getLong(7)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listTables;
	}

	@Override
	public ArrayList<Table> getTablesADresserOuRanger() {
		ArrayList<Table> listTables = new ArrayList<Table>();
		try {
			
			String sql = "SELECT * FROM rtable WHERE etat = 'SALE'";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			while(result.next()) {
				listTables.add(new Table(result.getLong(1), result.getLong(2), result.getString(3), result.getInt(4), result.getString(5), result.getLong(6), result.getLong(7)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listTables;
	}
	
	@Override
	public void dresserTable(long idTable) {
		try {
			
			String sql = "UPDATE rtable SET etat = 'PROPRE', avancement = 'VIDE' WHERE idtable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idTable);

			stmt.executeUpdate();		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reserverTable(long idClient, long numTable) {
		try {
			String sql = "UPDATE rtable SET idclient = ?, etat = 'RESERVEE' WHERE idTable = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idClient);
			stmt.setLong(2, numTable);
			
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
