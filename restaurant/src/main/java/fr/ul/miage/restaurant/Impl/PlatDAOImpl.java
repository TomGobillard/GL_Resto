package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.ul.miage.restaurant.dao.CompositionPlatDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;

public class PlatDAOImpl extends PlatDAO {
	@Override
	public Plat find(long id) {
		// TODO Auto-generated method stub
		Plat plat = new Plat();

		try {
			String sql = "SELECT * FROM plat WHERE idplat = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3), result.getBoolean(4), result.getLong(5), result.getLong(6));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return plat;
	}

	@Override
	public Plat create(Plat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plat update(Plat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Plat obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void creerPlat(String nom, double prix, ArrayList<Produit> ingredients, int idCateg) {
		// TODO Auto-generated method stub

		long idPlat;

		try {
			String sql = "INSERT INTO Plat (libelle, prix, isplatdujour, nbcommande, idcategorie) VALUES (?, ?, false, 0, ?)";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, nom);
			stmt.setDouble(2, prix);
			stmt.setInt(3, idCateg);

			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			String sql2 = "SELECT idplat FROM Plat WHERE libelle = ?";

			PreparedStatement stmt2 = connect.prepareStatement(sql2);
			stmt2.setString(1, nom);

			ResultSet result = stmt2.executeQuery();

			if (result.next()) {
				idPlat = result.getLong(1);

				String sql3;
				PreparedStatement stmt3;
				CompositionPlatDAO compositionPlatDAO = new CompositionPlatDAOImpl();

				//On créer les composiot de chaque plat
				for (Produit ingredient : ingredients) {
					CompositionPlat compoPlat = new CompositionPlat(ingredient.getId(), idPlat, ingredient.getQuantite());

					compositionPlatDAO.create(compoPlat);
				}
			}
		} catch (Exception e) {

		}
	}

	public ArrayList<Plat> listerPlatSelonCategorie(long idCateg) {
		ArrayList<Plat> listPlats = new ArrayList<>();
		try {
			String sql2 = "SELECT * FROM plat WHERE idcategorie = ?";
			PreparedStatement stmt2 = connect.prepareStatement(sql2);
			stmt2.setLong(1, idCateg);
			ResultSet result2 = stmt2.executeQuery();
			while (result2.next()) {
				Plat plat = new Plat(result2.getLong(1), result2.getString(2), result2.getDouble(3),
						result2.getBoolean(4), result2.getLong(5), result2.getLong(6));
				listPlats.add(plat);
			}
		} catch (Exception e) {

		}

		return listPlats;
	}

	@Override
	public ArrayList<Plat> getCarteduJour() {
		ArrayList<Plat> carteduJour = new ArrayList<>();
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM Plat WHERE isplatdujour = true";
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				Plat plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3), result.getBoolean(4),
						result.getLong(5), result.getLong(6));
				carteduJour.add(plat);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return carteduJour;
	}

	@Override
	public void initCarteduJour() {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE plat SET isplatdujour = false WHERE isplatdujour = true";
			PreparedStatement stmt = connect.prepareStatement(sql);

			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void ajoutPlatCarteduJour(long idPlat) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE Plat SET isplatdujour=true WHERE idPlat = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idPlat);

			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void incrementeNbCommandes(ArrayList<Plat> plats) {
		for (Plat p : plats) {
			try {
				String sql = "UPDATE plat SET nbcommande = nbcommande + 1 WHERE idplat = ?";
				PreparedStatement stmt = connect.prepareStatement(sql);
				stmt.setLong(1, p.getId());

				stmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	@Override
	public ArrayList<Plat> platsDispoCateg(long idCateg) {
		ArrayList<Plat> plats = new ArrayList<>();

		try {
			CompositionPlatDAO compoPlatDAO = new CompositionPlatDAOImpl();

			String sql2 = "SELECT * FROM plat WHERE idcategorie = ?";
			PreparedStatement stmt2 = connect.prepareStatement(sql2);
			stmt2.setLong(1, idCateg);
			ResultSet result2 = stmt2.executeQuery();

			while (result2.next()) {
				Plat plat = new Plat(result2.getLong(1), result2.getString(2), result2.getDouble(3),
						result2.getBoolean(4), result2.getLong(5), result2.getLong(6));

				if (compoPlatDAO.isDispo(plat.getId())) {
					plats.add(plat);
				}
			}

		} catch (Exception e) {

		}

		return plats;
	}

	@Override
	public ArrayList<Plat> getAll() {
		// TODO Auto-generated method stub
		ArrayList<Plat> plats = new ArrayList<>();
		Plat plat;

		try {
			String sql = "SELECT * FROM plat";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			while(result.next()) {
				plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3), result.getBoolean(4), 
						result.getLong(5), result.getLong(6));
				plats.add(plat);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}

		return plats;
	}

	public void setEtatPlatPret(long idPlat, long idCmde) {
		String newEtat = "PRETE";

		try {
			String sql = "UPDATE COMPOSITION_CMDE SET etat = ? WHERE idplat = ? AND idcommande = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, newEtat);
			stmt.setLong(2, idPlat);
			stmt.setLong(3, idCmde);
			System.out.println("Le plat est maintenant prêt !");

			stmt.executeUpdate();
		} catch (Exception e) {

		}
	}

	public boolean platExists(long idPlat) {
		String sql = "SELECT * FROM PLAT WHERE idplat = ?";
		boolean res = false;
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idPlat);

			ResultSet result = stmt.executeQuery();
			res = result.next();

		} catch (Exception e) {
			res = false;
		}
		return res;
	}

	@Override
	public ArrayList<Plat> platsPopulaires() {
		// TODO Auto-generated method stub
		ArrayList<Plat> listPlats = new ArrayList<>();

		try {
			String sql = "SELECT * FROM plat ORDER BY nbcommande DESC";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			Plat plat;
			while(result.next()) {
				plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3), result.getBoolean(4), 
						result.getLong(5), result.getLong(6));

				listPlats.add(plat);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listPlats;
	}

	@Override
	public boolean isPlatEnPreparation(long idPlat, long idCmde) {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT * FROM composition_cmde WHERE idplat = ? AND idcommande = ? AND etat = 'EN PREPARATION'";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idPlat);
			stmt.setLong(2, idCmde);
			
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}