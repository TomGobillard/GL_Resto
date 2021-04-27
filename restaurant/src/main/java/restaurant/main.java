package restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Models.Personnel;
import Models.Produit;
import database.DAO;
import database.PersonnelDAO;
import database.PersonnelDAOImpl;
import database.ProduitDAO;
import database.ProduitDAOImpl;

public class main {

	static boolean connected=false;

	public static void main(String[] args) {

		do {
			System.out.println("---------------------------------------------------------");
			System.out.println("Veuillez vous connecter pour avoir accès à l'application.");
			System.out.println("---------------------------------------------------------\n");

			int c;
			do {
				System.out.println("Que souhaitez-vous faire ?\n");
				System.out.println("Se connecter (1)");
				System.out.println("Quitter (2)");

				Scanner s = new Scanner(System.in);
				c = s.nextInt();
				switch (c) {
				case 1:
					connexion();
					break;
				case 2:
					System.out.println("Fermeture de l'application.");
					System.exit(0);
					break;
				default:
					System.out.println("Erreur de choix, réessayez.\n");
				}

			} while (c != 2 && c != 1);

			System.out.println("--------------------------------------------------");
			System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
			System.out.println("--------------------------------------------------\n");

			do {
				fctCuisinier();

				//				int c2;
				//				do {
				//					System.out.println("Que souhaitez-vous faire ?\n");
				//					System.out.println("Consulter les stocks (1)");
				//					System.out.println("Se déconnecter (2)");
				//					System.out.println("Quitter (3)");
				//
				//					Scanner s = new Scanner(System.in);
				//					c2 = s.nextInt();
				//					switch (c2) {
				//					case 1:
				//						consulterStocks();
				//						break;
				//					case 2:
				//						deconnexion();
				//						break;
				//					case 3:
				//						System.out.println("Fermeture de l'application.");
				//						System.exit(0);
				//						break;
				//					default:
				//						System.out.println("Erreur de choix, réessayez.\n");
				//					}
				//
				//				} while (c > 3 || c < 1);
			}while(connected);


		}while(!connected);






	}

	private static void fctCuisinier() {
		int c;
		do {
			System.out.println("Que souhaitez-vous faire ?\n");
			System.out.println("Consulter les stocks (1)");
			System.out.println("Créer plat (2)");
			System.out.println("Se déconnecter (3)");
			System.out.println("Quitter (4)");

			Scanner s = new Scanner(System.in);
			c = s.nextInt();
			switch (c) {
			case 1:
				consulterStocks();
				break;
			case 2:
				creerPlat();
				break;
			case 3:
				deconnexion();
				break;
			case 4:
				System.out.println("Fermeture de l'application.");
				System.exit(0);
				break;
			default:
				System.out.println("Erreur de choix, réessayez.\n");
			}

		} while (c > 3 || c < 1);


	}

	private static void deconnexion() {
		connected=false;		
	}

	private static void consulterStocks() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();

		ArrayList<Produit> produits = new ArrayList<Produit>();
		produits = produitDAO.listProduit();

		System.out.println("Liste des stocks :\n");
		for (Produit produit : produits) {
			System.out.println(produit.getLibelle() + " : " + produit.getQuantite());
		}
		System.out.println();
	}

	private static void creerPlat() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();

		boolean test = produitDAO.isDispo(1, 75);
		boolean test2 = produitDAO.isDispo(1, 59);


		HashMap<Long, Produit> listProduits = new HashMap<Long, Produit>();

		listProduits = produitDAO.listProduits();

		System.out.println("Liste des ingrédients disponibles : ");

		for(Long idProduit : listProduits.keySet()) {
			System.out.print("Id: " + listProduits.get(idProduit).getId());
			System.out.print(";	" + listProduits.get(idProduit).getLibelle());
			System.out.println(" : " + listProduits.get(idProduit).getQuantite());
		}

		System.out.println();

		System.out.println("Choisir un ingrédient à ajouter à la recette (par son Id)");


		boolean error = true;
		int idIngredient;

		while(error == true) {
			try {
				Scanner sc = new Scanner(System.in);
				idIngredient = sc.nextInt();
				error = false;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique");
			}
		}


	}

	private static void connexion() {

		System.out.println("Veuillez renseigner votre nom d'utilisateur.");
		String username;
		Scanner s = new Scanner(System.in);
		username=s.nextLine();

		System.out.println("Veuillez renseigner votre mot de passe.");
		String password;
		password=s.nextLine();

		//		PersonnelDAO<Personnel> personnelDAO = new PersonnelDAOImpl();
		//
		//		System.out.println(personnelDAO.connection(username, password));

		connected=true;


	}

}
