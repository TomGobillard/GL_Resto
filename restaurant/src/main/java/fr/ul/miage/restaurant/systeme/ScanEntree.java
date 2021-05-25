package fr.ul.miage.restaurant.systeme;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Table;

public class ScanEntree {

	public static int readInteger() {

		boolean error = true;

		int intSelect = 0;

		while (error) {
			try {
				Scanner sc = new Scanner(System.in, "UTF-8");
				intSelect = sc.nextInt();
				error = false;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique");
			}
		}
		return intSelect;
	}

	public static int readIntegerWithDelimitations(int min, int max) {
		boolean error = true;

		int intSelect = 0;

		while (error) {
			try {
				Scanner sc = new Scanner(System.in, "UTF-8");
				intSelect = sc.nextInt();
				if (intSelect >= min && intSelect <= max) {
					error = false;
				} else {
					System.out.println("Choix hors limites");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique");
			}
		}

		return intSelect;
	}

	public static double readDoubleWithDelimitations(int min, int max) {
		boolean error = true;

		double doubleSelect = 0;

		while (error) {
			try {
				Scanner sc = new Scanner(System.in, "UTF-8");
				doubleSelect = sc.nextDouble();
				if (doubleSelect >= min && doubleSelect <= max) {
					error = false;
				} else {
					System.out.println("Choix hors limites");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique (séparé par un ','");
			}
		}

		return doubleSelect;
	}


	public static long readIdTable(ArrayList<Table> tables, String msg) {
		boolean error = true;
		long idTable = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro de la table " + msg);
				Scanner s = new Scanner(System.in, "UTF-8");
				idTable = s.nextLong();
				for (Table table : tables) {
					if (idTable == table.getId()) {
						error = false;
					}
				}
				if (error) {
					System.out.println("L'id de la table renseignée n'existe pas.");
				}
			} catch (Exception e) {
				System.out.println("Choix incorrect.");
			}

		}
		return idTable;
	}
	
	public static long readIdPlat(ArrayList<Plat> plats, String msg) {
		boolean error = true;
		long idPlat = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro du plat " + msg);
				Scanner s = new Scanner(System.in, "UTF-8");
				idPlat = s.nextLong();
				for (Plat plat: plats) {
					if (idPlat == plat.getId()) {
						error = false;
					}
				}
				if (error) {
					System.out.println("L'id du plat renseignée n'existe pas.");
				}
			} catch (Exception e) {
				System.out.println("Choix incorrect.");
			}

		}
		return idPlat;
	}
	
	
	
	public static long readId(ArrayList<Integer> tables, String msg) {
		boolean error = true;
		TableDAO tableDAO = new TableDAOImpl();
		long idTable = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro de la table " + msg);
				Scanner s = new Scanner(System.in, "UTF-8");
				idTable = s.nextLong();
				for (Integer i : tables) {
					if (idTable == i) {
						error = false;
					}
				}
				if (error) {
					System.out.println("L'id de la table renseignée n'existe pas.");
				}
			} catch (Exception e) {
				System.out.println("Choix incorrect.");
			}

		}
		return idTable;
	}
}
