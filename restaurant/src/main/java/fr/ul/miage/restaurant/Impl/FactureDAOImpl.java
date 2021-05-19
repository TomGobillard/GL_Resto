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
											
					

				} catch (Exception e) {
					// TODO: handle exception
				}
				

			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return facture;
	}
	
	
}
