package restaurant;

import java.util.Scanner;

import Models.Personnel;
import database.DAO;
import database.PersonnelDAO;
import database.PersonnelDAOImpl;

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
			
				int c2;
				do {
					System.out.println("Que souhaitez-vous faire ?\n");
					System.out.println("Consulter les stocks (1)");
					System.out.println("Se déconnecter (2)");
					System.out.println("Quitter (3)");

					Scanner s = new Scanner(System.in);
					c2 = s.nextInt();
					switch (c2) {
					case 1:
						consulterStocks();
						break;
					case 2:
						deconnexion();
						break;
					case 3:
						System.out.println("Fermeture de l'application.");
						System.exit(0);
						break;
					default:
						System.out.println("Erreur de choix, réessayez.\n");
					}

				} while (c > 3 || c < 1);
			}while(connected);
			
			
		}while(!connected);

	
			



	}

	private static void deconnexion() {
		connected=false;		
	}

	private static void consulterStocks() {
		System.out.println("Liste des stocks :\n");		
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
