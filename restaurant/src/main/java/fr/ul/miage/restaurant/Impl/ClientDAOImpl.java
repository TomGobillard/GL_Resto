package fr.ul.miage.restaurant.Impl;

import fr.ul.miage.restaurant.models.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import fr.ul.miage.restaurant.dao.ClientDAO;

public class ClientDAOImpl extends ClientDAO {

	@Override
	public Client find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client create(Client obj) {
		// TODO Auto-generated method stub
		Client client = new Client();
		try {
			String sql = "INSERT INTO client (heurearrivee, heuredepart) VALUES (current_timestamp, current_timestamp)";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet result = stmt.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			String sql2 = "SELECT MAX(idclient) FROM client";
			PreparedStatement stmt2 = connect.prepareStatement(sql2);

			ResultSet result2 = stmt2.executeQuery();

			if (result2.next()) {
				client = new Client(result2.getLong(1), null, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return client;
	}

	@Override
	public Client update(Client obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Client obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Client> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Timestamp getRotationTimeAvg() {
		Timestamp timestamp = new Timestamp(0);
		
		try {
			String sql = "SELECT AVG (heuredepart- heurearrivee) AS temps_Rotation_moyen FROM client";
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				timestamp = result.getTimestamp(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return timestamp;

	}
}
