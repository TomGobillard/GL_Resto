package fr.ul.miage.restaurant.menus;

import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ServeurDAOImpl;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ServeurDAO;
import fr.ul.miage.restaurant.models.Personnel;

public class MenuServeur extends MenuCommun {

	public MenuServeur(boolean connected, Personnel user) {
		super(connected, user);
		// TODO Auto-generated constructor stub
	}
	
	public void printMenuServeur() {
		System.out.println("--------------------------------------------------");
		System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Vous êtes connecté en tant que serveur");

		
		do {

			int c2;
			do {

				printOptions();

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
					PlatDAO platDAO = new PlatDAOImpl();
					platDAO.listerPlatSelonCategorie();
					break;
				case 20:
					deconnexion();
					break;
				case 21:
					System.out.println("Fermeture de l'application.");
					System.exit(0);
					break;
				default:
					System.out.println("Erreur de choix, réessayez.\n");
					break;
				}

			} while (c2 != 20);
		} while(connected);
	}

	public void printOptions() {
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");
		
		System.out.println("Consulter les stocks (1)");
		System.out.println("Consulter l'état d'occupation des tables (2)");
		System.out.println("Consulter les plats par catégorie (3)");
		
		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
	}
}
