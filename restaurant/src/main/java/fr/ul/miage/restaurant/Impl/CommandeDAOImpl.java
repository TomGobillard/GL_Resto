package fr.ul.miage.restaurant.Impl;

import fr.ul.miage.restaurant.models.Commande;
import fr.ul.miage.restaurant.models.Produit;

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

	public String getCommandeEntrantes() {
		String ligneResultat = "";
		try {
			String sql = "SELECT * FROM COMMANDE WHERE etat = 'EN PREPARATION'";

			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				System.out.println("1");
				try {
					ligneResultat += "Commande n°" + result.getInt(1) + " (" + result.getTime(3) + " - Table n°" + result.getInt(5) + ") : ";
					
					String sql2 = "SELECT * FROM COMPOSITION_CMDE WHERE idcommande = ?";
					PreparedStatement stmt2 = connect.prepareStatement(sql2);
					stmt2.setLong(1, result.getInt(1));
					ResultSet result2 = stmt2.executeQuery();
					
					while(result2.next()) {
						String sql3 = "SELECT * FROM PLAT WHERE idplat = ? AND etat = 'EN PREPARATION'";
						PreparedStatement stmt3 = connect.prepareStatement(sql3);
						stmt3.setLong(1, result2.getInt(2));
						ResultSet result3 = stmt3.executeQuery();
						
						while(result3.next()) {
							 ligneResultat += result3.getInt(3) + " " + result3.getString(2) + "\n"; 
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ligneResultat;
	}
}
