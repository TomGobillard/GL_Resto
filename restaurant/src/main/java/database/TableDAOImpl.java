package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import Models.Table;

public class TableDAOImpl extends TableDAO {
	@Override
	public Table find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table create(Table obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table update(Table obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Table obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void obtenirInfoTable() {
		boolean reqFind = false;
		while (!reqFind) {
			System.out.println("Veuillez renseigner l'id de la table.");
			Scanner s = new Scanner(System.in);
			int choix = s.nextInt();
			String sql = "SELECT * FROM rtable WHERE idtable = ?";

			try {
				PreparedStatement stmt = connect.prepareStatement(sql);
				stmt.setLong(1, choix);

				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					reqFind = true;
					Table t = new Table(result.getLong(1), result.getLong(2), result.getLong(3), result.getString(4),
							result.getLong(5), result.getString(6), result.getLong(7));
					System.out.println(t);
				} else {
					System.out.println("Il n'y a pas de table enregistrée pour cet identifiant.");
				}
			} catch (Exception e) {

			}
		}
	}
}
