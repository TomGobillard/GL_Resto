package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.ul.miage.restaurant.dao.CompositionPlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Plat;

public class CompositionPlatDAOImpl extends CompositionPlatDAO {

	@Override
	public CompositionPlat find(long id) {
		return null;
	}

	@Override
	public CompositionPlat create(CompositionPlat obj) {
		try {
			String sql = "INSERT INTO composition_plat VALUES (?, ?, ?)";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, obj.getIdProduit());
			stmt.setLong(2, obj.getIdPlat());
			stmt.setInt(3, obj.getQuantite());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CompositionPlat update(CompositionPlat obj) {
		return null;
	}

	@Override
	public void delete(CompositionPlat obj) {}

	@Override
	public ArrayList<CompositionPlat> getWithPlats(ArrayList<Plat> plats) {
		ArrayList<CompositionPlat> compoPlats = new ArrayList<>();
		for (Plat p : plats) {

			try {

				ResultSet result = this.connect
						.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
						.executeQuery("SELECT * FROM composition_plat WHERE idplat = \'" + p.getId() + "\'");
				while (result.next()) {
					CompositionPlat compoPlat = new CompositionPlat(result.getLong(1), result.getLong(2), result.getInt(3));
					compoPlats.add(compoPlat);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return compoPlats;
	}

	@Override
	public boolean isDispo(long idPlat) {
		ProduitDAO produitDAO = new ProduitDAOImpl();

		try {
			String sql = "SELECT * FROM composition_plat WHERE idplat = ?";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idPlat);

			ResultSet result = stmt.executeQuery();

			while(result.next()) {
				if(!produitDAO.isDispo(result.getLong(1), result.getInt(3))) {
					return false;
				}
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public ArrayList<CompositionPlat> getAll() {
		return null;
	}
}
