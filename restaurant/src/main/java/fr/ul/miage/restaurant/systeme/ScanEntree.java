package fr.ul.miage.restaurant.systeme;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.models.Serveur;
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
	
	public static String readString() {

		boolean error = true;

		String entry = "";

		while (error) {
			try {
				Scanner sc = new Scanner(System.in, "UTF-8");
				entry = sc.nextLine();
				error = false;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur alphabétique");
			}
		}
		return entry;
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
					System.out.println("Choix hors limites [" + min + ";" + max + "]");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique (séparé par le caractère ',')");
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
				Scanner sc = new Scanner(System.in, "UTF-8");
				idTable = sc.nextLong();
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
	
	public static long readIdProduit(ArrayList<Produit> produits, String msg) {
		boolean error = true;
		long idProduit = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro du produit " + msg);
				Scanner sc = new Scanner(System.in, "UTF-8");
				idProduit = sc.nextLong();
				for (Produit produit : produits) {
					if (idProduit == produit.getId()) {
						error = false;
					}
				}
				if (error) {
					System.out.println("L'id du produit renseignée n'existe pas.");
				}
			} catch (Exception e) {
				System.out.println("Choix incorrect.");
			}

		}
		return idProduit;
	}
	
	public static long readIdPersonnel(ArrayList<Personnel> personnels, String msg) {
		boolean error = true;
		long idPersonnel = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro du personnel " + msg);
				Scanner sc = new Scanner(System.in, "UTF-8");
				idPersonnel = sc.nextLong();
				for (Personnel personnel : personnels) {
					if (idPersonnel == personnel.getId()) {
						error = false;
					}
				}
				if (error) {
					System.out.println("L'id du personnel renseignée n'existe pas.");
				}
			} catch (Exception e) {
				System.out.println("Choix incorrect.");
			}

		}
		return idPersonnel;
	}
	
	public static long readIdServeur(ArrayList<Serveur> serveurs, String msg) {
		boolean error = true;
		long idServeur = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro du serveur " + msg);
				Scanner sc = new Scanner(System.in, "UTF-8");
				idServeur = sc.nextLong();
				for (Serveur serveur : serveurs) {
					if (idServeur == serveur.getId()) {
						error = false;
					}
				}
				if (error) {
					System.out.println("L'id du serveur renseigné n'existe pas.");
				}
			} catch (Exception e) {
				System.out.println("Choix incorrect.");
			}

		}
		return idServeur;
	}
	
	public static long readIdPlat(ArrayList<Plat> plats, String msg) {
		boolean error = true;
		long idPlat = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro du plat " + msg);
				Scanner sc = new Scanner(System.in, "UTF-8");
				idPlat = sc.nextLong();
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
		long idTable = -1;

		while (error) {
			try {
				System.out.println("Veuillez renseignez le numéro de la table " + msg);
				Scanner sc = new Scanner(System.in, "UTF-8");
				idTable = sc.nextLong();
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
