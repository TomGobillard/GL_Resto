package fr.ul.miage.restaurant.Impl;

import fr.ul.miage.restaurant.models.Commande;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.ul.miage.restaurant.dao.CommandeDAO;

public class CommandeDAOImpl extends CommandeDAO{
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
		int idLastCommande=-1;
		
		try {
			String sql = "SELECT MAX(idcommande) FROM Commande";
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				idLastCommande = result.getInt(1);
			}
		}
		catch(Exception e){
			
		}
		return idLastCommande;
	}

	@Override
	public void creerCompositionCmde(int idCommande, int idPlat) {
		try {
			String sql = "INSERT INTO Composition_cmde (idcommande, idplat) VALUES (?,?)";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, idCommande);
			stmt.setInt(2, idPlat);

			stmt.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		}			
	}
}
