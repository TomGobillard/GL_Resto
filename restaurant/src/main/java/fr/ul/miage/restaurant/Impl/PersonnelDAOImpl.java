package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.dao.PersonnelDAO;

public class PersonnelDAOImpl extends PersonnelDAO<Personnel> {

	@Override
	public Personnel find(long id) {
		// TODO Auto-generated method stub
		Personnel personnel = new Personnel();

//		try {
//			ResultSet result = this.connect
//					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
//					.executeQuery("SELECT * FROM personnel WHERE identifiant = \'0\'");
//
//			if (result.first()) {
//				System.out.println(result.getString("role"));
//				personnel = new Personnel(id, result.getString("role"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return personnel;
	}

	@Override
	public Personnel create(Personnel obj) {
		// TODO Auto-generated method stub		
		try {
			String sql = "INSERT INTO personnel VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, obj.getId());
			stmt.setString(2, obj.getRole());
			
			ResultSet result = stmt.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return obj;
	}

	@Override
	public Personnel update(Personnel obj) {
		// TODO Auto-generated method stub
		
		try {
			String sql = "UPDATE personnel SET id = ?, role = ? WHERE idpersonnel = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, obj.getId());
			stmt.setString(2, obj.getRole());
			
			ResultSet result = stmt.executeQuery();
			
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
