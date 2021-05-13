package fr.ul.miage.restaurant.postgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgresSQL {

	private static String url = "jdbc:postgresql://plg-broker.ad.univ-lorraine.fr:5432/Resto_G10";

	private static String user = "m1user1_18";

	private static String passwd = "m1user1_18";

	private static Connection connect;

	public static Connection getInstance() {
		if (connect == null) {
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				System.err.println("Vous devez être connecté au serveur de l'IDMC pour pouvoir utiliser l'application");
			}
		}

		return connect;
	}
}
