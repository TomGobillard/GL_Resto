package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.dao.ServeurDAO;

public class ServeurDAOImpl extends ServeurDAO {

	private Serveur serveur;

	public ServeurDAOImpl(Personnel serveur) {
		this.serveur = (Serveur) serveur;
	}

	public ServeurDAOImpl() {
		this.serveur = null;
	}

	@Override
	public Serveur find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serveur create(Serveur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serveur update(Serveur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Serveur obj) {
		// TODO Auto-generated method stub

	}

	public boolean serveurExists(long idServeur) {
		String sql = "SELECT * FROM SERVEUR WHERE idserveur = ?";
		boolean res = false;
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idServeur);

			ResultSet result = stmt.executeQuery();
			res = result.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public ArrayList<Serveur> getAll() {
		// TODO Auto-generated method stub
		ArrayList<Serveur> listServeur = new ArrayList<Serveur>();

		try {
			String sql = "SELECT * FROM serveur";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

			Serveur serveur;
			while (result.next()) {
				serveur = new Serveur(result.getLong(1), result.getString(7), result.getString(8), result.getLong(5),
						result.getInt(6));
				listServeur.add(serveur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listServeur;
	}

}
