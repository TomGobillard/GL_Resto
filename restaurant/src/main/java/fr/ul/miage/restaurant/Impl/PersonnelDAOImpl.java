package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.dao.PersonnelDAO;

public class PersonnelDAOImpl extends PersonnelDAO<Personnel> {

	@Override
	public Personnel find(long id) {
		// TODO Auto-generated method stub
		Personnel personnel = new Personnel();

		try {
			String sql = "SELECT * FROM personnel WHERE id = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {
				personnel = new Personnel(result.getLong(1), result.getString(4), result.getString(2), 
						result.getString(3), result.getString(5), result.getString(6));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return personnel;
	}

	@Override
	public Personnel create(Personnel obj) {
		try {
			String sql = "INSERT INTO personnel (identifiant, mdp, role, prenom, nom) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, obj.getLogin());
			stmt.setString(2, obj.getMdp());
			stmt.setString(3, obj.getRole());
			stmt.setString(4, obj.getPrenom());
			stmt.setString(5, obj.getNom());

			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnel update(Personnel obj) {
		try {
			String sql = "UPDATE personnel SET identifiant=?, mdp=?, role=?, prenom=?, nom=?"
					+ "WHERE id = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, obj.getLogin());
			stmt.setString(2, obj.getMdp());
			stmt.setString(3, obj.getRole().toUpperCase());
			stmt.setString(4, obj.getPrenom());
			stmt.setString(5, obj.getNom());
			stmt.setLong(6, obj.getId());

			stmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		
		try {
			String sql = "UPDATE personnel SET id = ?, role = ? WHERE idpersonnel = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, obj.getId());
			stmt.setString(2, obj.getRole());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return obj;
	}

	@Override
	public void delete(Personnel obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public Personnel connection(String login, String mdp) {
		Personnel personnel = null;

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT * FROM personnel WHERE identifiant = \'" + login + "\' AND mdp = \'" + mdp + "\'");

			if (result.first()) {
				if(result.getString("role").toUpperCase().equals("SERVEUR")) {
					ResultSet result2 = this.connect
							.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
									"SELECT * FROM serveur WHERE identifiant = \'" + login + "\' AND mdp = \'" + mdp + "\'");
					if (result2.first()) {
						personnel = new Serveur(result2.getLong("idserveur"), result2.getInt("etage"));
					}
				} else {
					personnel = new Personnel(result.getLong("id"), result.getString("role"));
				}
				System.out.println("Connexion réussie !");
			} else {
				System.out.println("L'identifiant ou le mot de passe est incorrect.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personnel;
	}

	@Override
	public ArrayList<Personnel> getAll() {
		ArrayList<Personnel> listPersonnel = new ArrayList<Personnel>();

		try {
			String sql = "SELECT * FROM personnel";

			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while(result.next()) {
				Personnel personel = new Personnel(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
				listPersonnel.add(personel);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return listPersonnel;
	}

}
