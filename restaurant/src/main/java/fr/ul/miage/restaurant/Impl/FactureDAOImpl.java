package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import fr.ul.miage.restaurant.dao.FactureDAO;
import fr.ul.miage.restaurant.models.Facture;

public class FactureDAOImpl extends FactureDAO {
	@Override
	public Facture find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facture create(Facture obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facture update(Facture obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Facture obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Facture> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Facture genererFacture(long idclient, String repas) {
		Facture facture = new Facture();

		try {
			String sql = "SELECT P.idplat, quantite, prix "
					+ "FROM Commande C, Composition_cmde CC, Plat P "
					+ "WHERE CC.idplat = P.idplat "
					+ "AND C.idcommande = CC.idcommande "
					+ "AND c.idclient = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idclient);

			ResultSet result = stmt.executeQuery();
			double prixFacture = 0;
			while(result.next()) {
				prixFacture += result.getInt(2) * result.getDouble(3);
			}

			try {
				String sql2 = "INSERT INTO facture(montant, repas, idclient, date) VALUES(?, ?, ?, now()::timestamptz)";

				PreparedStatement stmt2 = connect.prepareStatement(sql2);


				stmt2.setDouble(1, prixFacture);
				stmt2.setString(2, repas);			
				stmt2.setLong(3, idclient);
				stmt2.executeUpdate();

				try {
					String sql3 = "SELECT * FROM facture ORDER BY idfacture DESC LIMIT 1";
					PreparedStatement stmt3 = connect.prepareStatement(sql3);

					ResultSet result3 = stmt3.executeQuery();

					if(result3.next()) {
						java.sql.Timestamp timestamp = result3.getTimestamp(5); // O/P: DD:MM:YYYY HH:mm:ss
						Date date = new Date(timestamp.getTime());
						facture = new Facture(result3.getLong(1), result3.getDouble(2), result3.getString(3), result3.getLong(4), date);
					}



					String sql4 = "UPDATE rtable SET etat = 'SALE', avancement = 'FINI', idclient = null WHERE idclient = ?";

					PreparedStatement stmt4 = connect.prepareStatement(sql4);
					stmt4.setLong(1, idclient);

					stmt4.executeUpdate();
					
					String sql5 = "UPDATE client SET heuredepart = now()::timestamptz WHERE idclient = ?";

					PreparedStatement stmt5 = connect.prepareStatement(sql5);
					stmt5.setLong(1, idclient);

					stmt5.executeUpdate();

				} catch (Exception e) {
					e.printStackTrace();
				}


			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return facture;
	}

	@Override
	public int profitDejeuner() {
		// TODO Auto-generated method stub
		int profit=0;

		try {
			String sql = "SELECT sum(montant)\r\n"
					+ "FROM facture \r\n"
					+ "WHERE repas = 'DEJEUNER'\r\n"
					+ "AND DATE(date) = current_date";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				profit = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profit;
	}

	@Override
	public int profitDiner() {
		// TODO Auto-generated method stub
		int profit=0;

		try {
			String sql = "SELECT sum(montant)\r\n"
					+ "FROM facture \r\n"
					+ "WHERE repas = 'DINER'\r\n"
					+ "AND DATE(date) = current_date";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				profit = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profit;
	}

	@Override
	public int getRecetteQuotidienne() {
		// TODO Auto-generated method stub
		int recette = 0;

		try {
			String sql = "SELECT sum(montant)\r\n"
					+ "FROM facture\r\n"
					+ "WHERE DATE(date) = current_date";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				recette = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recette;
	}

	@Override
	public int getRecetteHebdo() {
		// TODO Auto-generated method stub
		int recette = 0;

		try {
			String sql = "SELECT sum(montant)\r\n"
					+ "FROM facture\r\n"
					+ "WHERE DATE(date) >= date_trunc('week', current_date)";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				recette = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recette;
	}

	@Override
	public int getRecetteMensuelle() {
		// TODO Auto-generated method stub
		int recette = 0;

		try {
			String sql = "SELECT sum(montant)\r\n"
					+ "FROM facture\r\n"
					+ "WHERE DATE(date) >= date_trunc('month', current_date)";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				recette = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recette;
	}


}
