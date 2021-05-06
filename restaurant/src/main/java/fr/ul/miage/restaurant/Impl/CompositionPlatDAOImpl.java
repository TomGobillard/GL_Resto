package fr.ul.miage.restaurant.Impl;

import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Plat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.ul.miage.restaurant.dao.CompositionPlatDAO;

public class CompositionPlatDAOImpl extends CompositionPlatDAO {

	@Override
	public CompositionPlat find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompositionPlat create(CompositionPlat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompositionPlat update(CompositionPlat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompositionPlat obj) {
		// TODO Auto-generated method stub

	}

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return compoPlats;
	}
}
