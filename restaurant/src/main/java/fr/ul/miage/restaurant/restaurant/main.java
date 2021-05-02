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
import fr.ul.miage.restaurant.menus.MenuCuisinier;
import fr.ul.miage.restaurant.menus.MenuDirecteur;

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

				case 2:
					ServeurDAO serveurDAO = new ServeurDAOImpl(user);
					serveurDAO.printOccupationAllTables();
					break;
				case 4:
					TableDAO tableDAO = new TableDAOImpl();
					tableDAO.obtenirInfoTable();
					break;
				case 5:
					PlatDAO platDAO = new PlatDAOImpl();
					platDAO.listerPlatSelonCategorie();
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
				
				switch(user.getRole().toUpperCase()) {
				case "CUISINIER":
					MenuCuisinier menuCuisinier = new MenuCuisinier(true, user);
					menuCuisinier.printMenuCuisinier();
					break;
					
				case "DIRECTEUR":
					MenuDirecteur menuDirecteur = new MenuDirecteur(true, user);
					menuDirecteur.printMenuDirecteur();
					break;
					
				default : 
					break;
				}
				
				printAccueil();
			}
		}
	}



}
