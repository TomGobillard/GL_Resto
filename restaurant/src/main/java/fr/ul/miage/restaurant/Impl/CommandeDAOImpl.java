package fr.ul.miage.restaurant.Impl;

import fr.ul.miage.restaurant.models.Commande;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.ul.miage.restaurant.dao.CommandeDAO;

public class CommandeDAOImpl extends CommandeDAO {
	@Override
	public Commande find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande create(Commande obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande update(Commande obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Commande obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCommandeEntrantes() {
		String ligneResultat = "";
		try {
			String sql = "SELECT * FROM COMMANDE WHERE etat = 'EN PREPARATION'";

			PreparedStatement stmt = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				try {
					ligneResultat += "Commande n°" + result.getInt(1) + " (" + result.getTime(3) + " - Table n°"
							+ result.getInt(5) + ") : ";

					String sql2 = "SELECT * FROM COMPOSITION_CMDE WHERE idcommande = ? AND etat = 'EN PREPARATION'";
					PreparedStatement stmt2 = connect.prepareStatement(sql2);
					stmt2.setLong(1, result.getInt(1));
					ResultSet result2 = stmt2.executeQuery();

					while (result2.next()) {
						String sql3 = "SELECT * FROM PLAT WHERE idplat = ?";
						PreparedStatement stmt3 = connect.prepareStatement(sql3);
						stmt3.setLong(1, result2.getInt(2));
						ResultSet result3 = stmt3.executeQuery();

						while (result3.next()) {
							ligneResultat += result2.getInt(4) + " " + result3.getString(2);
						}
						if (!result2.isLast()) {
							ligneResultat += ", ";
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				ligneResultat += "\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ligneResultat;
	}

	@Override
	public void creerCommande(int idTable) {

		try {
			String sql = "INSERT INTO Commande (heurecmdprete, heurecmdpassee, etat, idtable) VALUES (null, current_timestamp ,'EN PREPARATION', ?)";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, idTable);

			stmt.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public int getLastCommande() {
		int idLastCommande = -1;

		try {
			String sql = "SELECT MAX(idcommande) FROM Commande";
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				idLastCommande = result.getInt(1);
			}
		} catch (Exception e) {

		}
		return idLastCommande;
	}

	@Override
	public void creerCompositionCmde(int idCommande, int idPlat) {
		try {
			String sql = "INSERT INTO Composition_cmde (idcommande, idplat, etat) VALUES (?,?, 'EN PREPARATION'')";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, idCommande);
			stmt.setInt(2, idPlat);

			stmt.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void deleteLastCmdeforTest() {
		// TODO Auto-generated method stub
		try {
			Long idcmde = (long) 1;
			
			String sql = "SELECT idcommande FROM commande ORDER BY idcommande DESC";
			PreparedStatement stmt = connect.prepareStatement(sql);
			
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				idcmde = result.getLong(1);
			}
			
			String sqlDeleteCompoCmde = "DELETE FROM composition_cmde WHERE idcommande = ?";
			PreparedStatement stmtDeleteCompoCmde = connect.prepareStatement(sqlDeleteCompoCmde);
			stmtDeleteCompoCmde.setLong(1, idcmde);
			
			ResultSet resultDeleteCompocmde = stmtDeleteCompoCmde.executeQuery();
			
			String sqlDeleteCmde = "DELETE FROM commande WHERE idcommande = ?";
			PreparedStatement stmtDeleteCmde = connect.prepareStatement(sqlDeleteCmde);
			stmtDeleteCmde.setLong(1, idcmde);
			
			ResultSet resultDeleteCmde = stmtDeleteCmde.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public ArrayList<Commande> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
