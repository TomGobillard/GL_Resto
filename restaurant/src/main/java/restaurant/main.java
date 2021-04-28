package restaurant;

import java.util.ArrayList;
import java.util.Scanner;

import Models.Personnel;
import Models.Produit;
import database.PersonnelDAO;
import database.PersonnelDAOImpl;
import database.PlatDAO;
import database.PlatDAOImpl;
import database.ProduitDAO;
import database.ProduitDAOImpl;
import database.ServeurDAO;
import database.ServeurDAOImpl;

public class main {

	static boolean connected=false;
	static Personnel user;

	public static void main(String[] args) {

		do {
			System.out.println("---------------------------------------------------------");
			System.out.println("Veuillez vous connecter pour avoir acc�s � l'application.");
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
					System.out.println("Erreur de choix, r�essayez.\n");
				}

			} while (c != 2 && c != 1);			
			
		} while(!connected);

	}
	
	private static void printAccueil() {
		System.out.println("--------------------------------------------------");
		System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
		System.out.println("--------------------------------------------------\n");
		boolean tableAvancementPrinted = false;
		do {
		
			int c2;
			do {
	
				printOptions(tableAvancementPrinted);
				tableAvancementPrinted = true;
				
				Scanner s = new Scanner(System.in);
				c2 = s.nextInt();
				switch (c2) {
				case 1:
					consulterStocks();
					break;
				case 2:
					ServeurDAO serveurDAO = new ServeurDAOImpl(user);
					serveurDAO.printOccupationAllTables();
					break;
				case 3:
					creerPlat();
					break;
				case 20:
					deconnexion();
					connexion();
					break;
				case 21:
					System.out.println("Fermeture de l'application.");
					System.exit(0);
					break;
				default:
					System.out.println("Erreur de choix, réessayez.\n");
					break;
				}

			} while (c2 != 21);
		} while(connected);
	}

	private static void printOptions(boolean tableAvancementPrinted) {
		System.out.println("Que souhaitez-vous faire ?\n");
		System.out.println(user.getRole());
		if(user.getRole().toUpperCase().equals("SERVEUR")) {
			if(!tableAvancementPrinted) {
				ServeurDAO serveurDAO = new ServeurDAOImpl(user);
				serveurDAO.printOccupationTablesWithAvancement();
			}

			System.out.println("Consulter les stocks (1)");
			System.out.println("Consulter l'état d'occupation des tables (2)");
		} else if(user.getRole().toUpperCase().equals("CUISINIER")) {
			System.out.println("Créer plat (3)");
		} else {
			System.out.println("Consulter les stocks (1)");
		}
		
		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
	}

	private static void deconnexion() {
		connected=false;		
	}

	private static void consulterStocks() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();

		ArrayList<Produit> produits = new ArrayList<Produit>();
		produits = produitDAO.listProduit();

		System.out.println("Liste des stocks :\n");

		for(int i = 0; i < produits.size(); i++) {
			System.out.println(produits.get(i).getId() + "; " + produits.get(i).getLibelle() + " : " + produits.get(i).getQuantite());
		}

		//		for (Produit produit : produits) {
		//			System.out.println(produit.getLibelle() + " : " + produit.getQuantite());
		//		}

		System.out.println();
	}

	private static void creerPlat() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();
		ArrayList<Produit> produits =  produitDAO.listProduit();
		
		PlatDAO platDAO = new PlatDAOImpl();
		
		boolean error = true;
		
		System.out.println("Nom du plat : ");
		Scanner scNom = new Scanner(System.in);
		String nomPlat = scNom.nextLine();
		
		int prix = 0;
		
		System.out.println("Prix : ");
		
		while(error == true) {
			try {
				Scanner scPrix = new Scanner(System.in);
				prix = scPrix.nextInt();
				if(prix > 0) {
					error = false;
				} else {
					System.out.println("Le prix doit �tre positif");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur num�rique");
			}
		}
		

//		boolean test = produitDAO.isDispo(1, 75);
//		boolean test2 = produitDAO.isDispo(1, 59);

		//		HashMap<Long, Produit> listProduits = new HashMap<Long, Produit>();
		//
		//		listProduits = produitDAO.listProduits();
		//
		//		System.out.println("Liste des ingr�dients disponibles : ");
		//
		//		for(Long idProduit : listProduits.keySet()) {
		//			System.out.print("Id: " + listProduits.get(idProduit).getId());
		//			System.out.print(";	" + listProduits.get(idProduit).getLibelle());
		//			System.out.println(" : " + listProduits.get(idProduit).getQuantite());
		//		}
		//
		//		System.out.println();

		
		ArrayList<Produit> compoPlat = new ArrayList<Produit>();
		int ajouter = 0;

		do {
			consulterStocks();

			System.out.println("Choisir un ingr�dient � ajouter � la recette (par son Id)");

			error = true;
			int idIngredient = 0;

			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					idIngredient = sc.nextInt();

					if(idIngredient > 0 && idIngredient <= produits.size()) {
						error = false;
					} else {
						System.out.println("L'id est invalide");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur num�rique");
				}
			}

			Produit ingredient = produits.get(idIngredient-1);

			System.out.println("Quantit� : ");

			error = true;
			int qte = 0;

			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					qte = sc.nextInt();

					if(! produitDAO.isDispo(ingredient.getId(), qte)) {
						System.out.println("Le stock est isuffisant pour " + ingredient.getLibelle());
						System.out.println("Quantit� : ");
					} else {
						error = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur num�rique");
				}
			}
			
			ingredient.setQuantite(qte);
			compoPlat.add(ingredient);
			
			System.out.println("Composition actuelle du plat : ");
			
			for(Produit produitCompo : compoPlat) {
				System.out.println(produitCompo.getLibelle() + " : " + produitCompo.getQuantite());
			}
			
			System.out.println("Voulez-vous ajouter un autre ingr�dient ? (1 : oui, 2 : non)");
			
			error = true;
			
			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					ajouter = sc.nextInt();

					error = false;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur num�rique");
				}
			}
			

		} while(ajouter == 1);
		
		platDAO.creerPlat(nomPlat, prix, compoPlat);
		
	}

	private static void connexion() {
		while(!connected) {
			System.out.println("Veuillez renseigner votre nom d'utilisateur.");
			String username;
			Scanner s = new Scanner(System.in);
			username=s.nextLine();
			
			System.out.println("Veuillez renseigner votre mot de passe.");
			String password;
			password=s.nextLine();
			
			PersonnelDAO<Personnel> personnelDAO = new PersonnelDAOImpl();	
			Personnel personnel = personnelDAO.connection(username, password);

			if(personnel != null) {
				connected=true;
				user = personnel;
				printAccueil();
			}
		}
	}
	
	

}
