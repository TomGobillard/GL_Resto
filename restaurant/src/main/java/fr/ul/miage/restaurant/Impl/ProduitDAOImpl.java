package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Produit;

public class ProduitDAOImpl extends ProduitDAO<Produit> {
	
	@Override
	public Produit find(long id) {
		Produit produit = new Produit();

		try {
			String sql = "SELECT * FROM produit WHERE idProduit = ?";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet result = stmt.executeQuery();


			if(result.next())
				produit = new Produit(id, result.getString(2), result.getInt(3));			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produit;
	}

	@Override
	public Produit create(Produit obj) {
		// TODO Auto-generated method stub

		try {		
			String sql = "INSERT INTO produit (libelle, quantite) VALUES (?, ?)";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, obj.getLibelle());
			stmt.setInt(2, obj.getQuantite());

			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public Produit update(Produit obj) {
		// TODO Auto-generated method stub

		try {
			String sql = "UPDATE produit SET quantite = ? WHERE idproduit = ?";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, obj.getQuantite());
			stmt.setLong(2, obj.getId());

			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void delete(Produit obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Produit> listProduit() {
		// TODO Auto-generated method stub

		ArrayList<Produit> produits = new ArrayList<Produit>();

		try {
			String sql = "SELECT * FROM produit ORDER BY idproduit";

			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			//Object c =  result.getArray(0);
			Produit produit;

			while(result.next()) {
				produit = new Produit();
				produit.setId(result.getInt(1));
				produit.setLibelle(result.getString(2));
				produit.setQuantite(result.getInt(3));

				produits.add(produit);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return produits;
	}

	public HashMap<Long, Produit> listProduits(){
		HashMap<Long, Produit> produits = new HashMap<Long, Produit>();

		try {
			String sql = "SELECT * FROM produit";

			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			//Object c =  result.getArray(0);
			Produit produit;

			while(result.next()) {
				produit = new Produit();
				produit.setId(result.getInt(1));
				produit.setLibelle(result.getString(2));
				produit.setQuantite(result.getInt(3));

				//produits.add(produit.getId(), produit);
				produits.put(produit.getId(), produit);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return produits;

	}

	public boolean isDispo(long idProduit, int qte) {
		try {
			String sql = "SELECT quantite FROM produit WHERE idproduit = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setLong(1, idProduit);

			ResultSet result = stmt.executeQuery();

			if(result.next()) {

				int qteStock = result.getInt(1);

				if(qte <= qteStock) {
					return true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		return false;
	}

	@Override
	public void updateQuantite(CompositionPlat compoPlat) {
		try {
			String sql = "UPDATE produit SET quantite = quantite - ? WHERE idproduit = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, compoPlat.getQuantite());
			stmt.setLong(2, compoPlat.getIdProduit());

			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public ArrayList<Produit> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Produit> getProduitsDispos() {
		// TODO Auto-generated method stub
		ArrayList<Produit> produits = new ArrayList<Produit>();

		try {
			String sql = "SELECT * FROM produit WHERE quantite > 0 ORDER BY idproduit";

			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			//Object c =  result.getArray(0);
			Produit produit;

			while(result.next()) {
				produit = new Produit();
				produit.setId(result.getInt(1));
				produit.setLibelle(result.getString(2));
				produit.setQuantite(result.getInt(3));

				produits.add(produit);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return produits;
	}

	@Override
	public Produit findByName(String name) {
		Produit produit = new Produit();

		try {
			String sql = "SELECT * FROM produit WHERE libelle = ?";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, name);

			ResultSet result = stmt.executeQuery();

			if(result.next())
				produit = new Produit(result.getLong(1), name, result.getInt(3));			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produit;
	}


}
