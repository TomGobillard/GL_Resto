package fr.ul.miage.restaurant.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.dao.PlatDAO;

public class PlatDAOImpl extends PlatDAO {
	@Override
	public Plat find(long id) {
		// TODO Auto-generated method stub
		return null;
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
	public void creerPlat(String nom, int prix, ArrayList<Produit> ingredients, int idCateg) {
		// TODO Auto-generated method stub

		long idPlat;

		try {
			String sql = "INSERT INTO Plat (libelle, prix, isplatdujour, nbcommande, idcategorie) VALUES (?, ?, false, 0, ?)";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, nom);
			stmt.setInt(2, prix);
			stmt.setInt(3, idCateg);

			stmt.executeQuery();

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

				for (Produit ingredient : ingredients) {
					sql3 = "INSERT INTO composition_plat VALUES (?, ?, ?)";
					stmt3 = connect.prepareStatement(sql3);

					stmt3.setLong(1, ingredient.getId());
					stmt3.setLong(2, idPlat);
					stmt3.setInt(3, ingredient.getQuantite());

					try {
						stmt3.executeQuery();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} catch (Exception e) {

		}
	}

	public void listerPlatSelonCategorie() {
		String sql = "SELECT * FROM categorie_plat";
		int choix =-1;
		try {
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			boolean entryNotValid = true;
			while (entryNotValid) {
				System.out.println("Sélectionnez la catégorie : \n");
				while (result.next()) {
					CategoriePlat ctgPlat = new CategoriePlat(result.getLong(1), result.getString(2));
					System.out.println(ctgPlat.getLibelle() + " (" + ctgPlat.getId() + ")");
				}
				Scanner s = new Scanner(System.in);
				choix = s.nextInt();

				if (choix >= 1 || choix <= 5) {
					entryNotValid = false;
				} else {
					System.out.println("Veuillez renseignez un identifiant de catégorie valide.");
				}
			}

			try {
				String sql2 = "SELECT * FROM plat WHERE idcategorie = ?";
				PreparedStatement stmt2 = connect.prepareStatement(sql2);
				stmt2.setLong(1, choix);
				ResultSet result2 = stmt2.executeQuery();
				while (result2.next()) {
					Plat plat = new Plat(result2.getLong(1), result2.getString(2), result2.getDouble(3), result2.getBoolean(4), result2.getLong(5), result2.getLong(6));
					System.out.println(plat);
				}
			} catch (Exception e) {

			}

		} catch (Exception e) {

		}
	}
}