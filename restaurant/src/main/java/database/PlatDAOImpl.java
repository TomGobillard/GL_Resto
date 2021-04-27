package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Models.Plat;
import Models.Produit;

public class PlatDAOImpl extends PlatDAO{
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
	public void creerPlat(String nom, int prix, ArrayList<Produit> ingredients) {
		// TODO Auto-generated method stub

		long idPlat;

		try {
			String sql = "INSERT INTO Plat (libelle, prix, isplatdujour, nbcommande, idcategorie) VALUES (?, ?, false, 0, ?)";

			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, nom);
			stmt.setInt(2, prix);
			stmt.setInt(3, 2);

			stmt.executeQuery();

		} catch (Exception e) {
			// TODO: handle exception
		}		

		try {
			String sql2 = "SELECT idplat FROM Plat WHERE libelle = ?";

			PreparedStatement stmt2 = connect.prepareStatement(sql2);
			stmt2.setString(1, nom);

			ResultSet result = stmt2.executeQuery();

			if(result.next()) {
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
					}catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		} catch (Exception e) {

		}
	}
}
