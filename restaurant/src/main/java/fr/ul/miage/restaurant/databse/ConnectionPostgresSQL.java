package fr.ul.miage.restaurant.databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgresSQL {
	
	private static String url = "jdbc:postgresql://plg-broker.ad.univ-lorraine.fr:5432/Resto_G10";
	
	private static String user = "m1user1_18";
	
	private static String passwd = "m1user1_18";
	
	private static Connection connect;
	
	public static Connection getInstance() {
		connect = null;
		
		try {
			connect = DriverManager.getConnection(url, user, passwd);
			
			//System.out.println("Lien avec la base établie");
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
//		if (connect == null) {
//			try {
//				connect = DriverManager.getConnection(url, user, passwd);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		return connect;
	}
}
