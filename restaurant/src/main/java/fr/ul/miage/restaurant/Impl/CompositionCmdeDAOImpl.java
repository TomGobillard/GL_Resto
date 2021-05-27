package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.ul.miage.restaurant.dao.CompositionCmdeDAO;
import fr.ul.miage.restaurant.models.CompositionCmde;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;

public class CompositionCmdeDAOImpl extends CompositionCmdeDAO{

	private Serveur serveur;

	public CompositionCmdeDAOImpl(Personnel serveur) {
		this.serveur = (Serveur) serveur;
	}

	public CompositionCmdeDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public ArrayList<CompositionCmde> getCompoCmdesWithServeur(long idServeur) {
		ArrayList<CompositionCmde> compoCmdes  = new ArrayList<>();

		try {
			String sql = "SELECT T.idtable, CC.idcommande, libelle, P.idplat FROM plat P, composition_cmde CC, commande C, rtable T "
					+ "WHERE CC.idplat = P.idplat AND CC.idcommande = C.idcommande AND C.idtable = T.idtable AND CC.etat = 'PRETE' AND idserveur = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idServeur);
			ResultSet result = stmt.executeQuery();
			int i = 0; 
			while(result.next()) {
				System.out.println("Table "+result.getLong(1)+" : "+result.getString(3)+" ("+i+")");
				i++;
				CompositionCmde compoCmde = new CompositionCmde(result.getLong(2),result.getLong(4));
				compoCmdes.add(compoCmde);
			}
		
		} catch (Exception e) {

		}
		return compoCmdes;
	}
	
	public void setEtatPlatsServis(long idCommande,long idPlat) {

		try {
			String sql1 = "UPDATE COMPOSITION_CMDE SET etat = 'SERVIE' WHERE idplat = ? AND idcommande = ?";
			PreparedStatement stmt1 = connect.prepareStatement(sql1);
			stmt1.setLong(1, idPlat);
			stmt1.setLong(2, idCommande);

			stmt1.executeUpdate();

		} catch (Exception e) {

		}			
	}

	@Override
	public CompositionCmde find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompositionCmde create(CompositionCmde obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompositionCmde update(CompositionCmde obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompositionCmde obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<CompositionCmde> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
