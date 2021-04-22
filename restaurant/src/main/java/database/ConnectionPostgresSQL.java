package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgresSQL {
	
	private static String url = "jdbc:postgresql://localhost:5432/Resto";
	
	private static String user = "postgres";
	
	private static String passwd = "CSI/C0vid-19";
	
	private static Connection connect;
	
	public static Connection getInstance() {
		connect = null;
		
		try {
			connect = DriverManager.getConnection(url, user, passwd);
			
			System.out.println("Connection réussie");
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
