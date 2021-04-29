package fr.ul.miage.restaurant.restaurant;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.Impl.ServeurDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.dao.PersonnelDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.dao.ServeurDAO;
import fr.ul.miage.restaurant.dao.TableDAO;

public class main {

	static boolean connected=false;
	static Personnel user;

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
				case 4:
					TableDAO tableDAO = new TableDAOImpl();
					tableDAO.obtenirInfoTable();
					break;
				case 5:
					PlatDAO platDAO = new PlatDAOImpl();
					platDAO.listerPlatSelonCategorie();
					break;
				case 6:
					MajStocks();
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
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");

		if(user.getRole().toUpperCase().equals("SERVEUR")) {
			if(!tableAvancementPrinted) {
				ServeurDAO serveurDAO = new ServeurDAOImpl(user);
				serveurDAO.printOccupationTablesWithAvancement();
			}

			System.out.println("Consulter les stocks (1)");
			System.out.println("Consulter l'état d'occupation des tables (2)");
			System.out.println("Consulter les plats par catégorie (5)");

		} else if(user.getRole().toUpperCase().equals("CUISINIER")) {
			System.out.println("Créer plat (3)");

		} else if(user.getRole().toUpperCase().equals("ASSISTANT SERVICE")) {
			System.out.println("Obtenir les informations d'une table (4)");

		} else if(user.getRole().toUpperCase().equals("DIRECTEUR")) {
			System.out.println("Mettre à jour les stocks (6)");
		}
		else {
			System.out.println("Consulter les stocks (1)");
		}

		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
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

	private static void MajStocks() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();	
		ArrayList<Produit> listProduits;

		int continuer = 0;

		do {
			listProduits =  produitDAO.listProduit();

			consulterStocks();

			System.out.println("Quelle stock souhaitez vous mettre à jour : ");

			boolean error = true;

			int idProduit = 0;

			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					idProduit = sc.nextInt();
					if(idProduit > 0 && idProduit <= listProduits.size()) {
						error = false;
					} else {
						System.out.println("Produit inexistant");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur num�rique");
				}
			}

			System.out.println("Quantité à ajouter (max: 100)");

			int qte = 0;

			error = true;
			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					qte = sc.nextInt();
					if(qte > 0 && idProduit <= 100) {
						error = false;
					} else {
						System.out.println("Quantité invalide");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur num�rique");
				}
			}

			int newQte = listProduits.get(idProduit-1).getQuantite() + qte;

			Produit majProduit = new Produit(idProduit, listProduits.get(idProduit).getLibelle(), newQte);

			produitDAO.update(majProduit);
			
			System.out.println("Voulez-vous continuer à mettre à jour les stocks ? (1 : oui, 2 : non)");

			error = true;

			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					continuer = sc.nextInt();

					error = false;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur num�rique");
				}
			}

		} while (continuer == 1);

	}

	private static void creerPlat() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();
		PlatDAO platDAO = new PlatDAOImpl();
		CategoriePlatDAO categPlatDAO = new CategoriePlatDAOImpl();

		ArrayList<Produit> produits =  produitDAO.listProduit();

		ArrayList<CategoriePlat> listCateg = new ArrayList<>();
		listCateg = categPlatDAO.getAllCateg();

		for(CategoriePlat categ : listCateg) {
			System.out.println(categ.getId() + "; " + categ.getLibelle());
		}

		System.out.println("Dans quelle catégorie est votre plat ?");

		boolean error = true;

		int idCateg = 0;

		while(error == true) {
			try {
				Scanner scCateg = new Scanner(System.in);
				idCateg = scCateg.nextInt();
				if(idCateg > 0 && idCateg <= listCateg.size()) {
					error = false;
				} else {
					System.out.println("Catégorie inexistante");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur num�rique");
			}
		}

		idCateg -= 1;

		System.out.println(listCateg.get(idCateg).getLibelle());
		System.out.println();

		error = true;

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

		platDAO.creerPlat(nomPlat, prix, compoPlat, idCateg+1);

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
