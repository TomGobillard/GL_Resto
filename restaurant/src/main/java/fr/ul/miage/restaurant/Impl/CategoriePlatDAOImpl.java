package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.models.CategoriePlat;

public class CategoriePlatDAOImpl extends CategoriePlatDAO{
	@Override
	public CategoriePlat find(long id) {
		// TODO Auto-generated method stub
		CategoriePlat categoriePlat = new CategoriePlat();
		
		try {
			String sql = "SELECT * FROM categorie_plat WHERE idCategorie = ?";
			
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, id);
			
			ResultSet result = stmt.executeQuery();
			
			
			if(result.next())
				categoriePlat = new CategoriePlat(id, result.getString("libelle"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categoriePlat;
	}

	@Override
	public CategoriePlat create(CategoriePlat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriePlat update(CategoriePlat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CategoriePlat obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<CategoriePlat> getAllCateg() {
		ArrayList<CategoriePlat> listCateg = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM categorie_plat";
			PreparedStatement stmt = connect.prepareStatement(sql);
			
			ResultSet result = stmt.executeQuery();
			CategoriePlat categ;
			
			while(result.next()) {
				categ = new CategoriePlat(result.getLong(1), result.getString(2));
				listCateg.add(categ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listCateg;
	}

	@Override
	public ArrayList<CategoriePlat> getAll() {
		// TODO Auto-generated method stub
ArrayList<CategoriePlat> listCateg = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM categorie_plat";
			PreparedStatement stmt = connect.prepareStatement(sql);
			
			ResultSet result = stmt.executeQuery();
			CategoriePlat categ;
			
			while(result.next()) {
				categ = new CategoriePlat(result.getLong(1), result.getString(2));
				listCateg.add(categ);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listCateg;
	}

}
