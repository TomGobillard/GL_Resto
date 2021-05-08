package fr.ul.miage.restaurant.menus;

import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.ServeurDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.ServeurDAO;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.models.Personnel;

public class MenuMaitreHotel extends MenuCommun{

	public MenuMaitreHotel(boolean connected, Personnel user) {
		super(connected, user);
		// TODO Auto-generated constructor stub
	}
	
	public void printMenuMaitrehotel() {
		System.out.println("--------------------------------------------------");
		System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Vous êtes connecté en tant que maitre d'hotel");
		
		do {

			int c2;
			do {

				printOptions();

				Scanner s = new Scanner(System.in);
				c2 = s.nextInt();
				switch (c2) {
				case 1:
					assignerServeurATable();
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

	private void assignerServeurATable() {
		TableDAO tableDAO = new TableDAOImpl();
		ServeurDAO serveurDAO = new ServeurDAOImpl();
		
		boolean error = true;
		
		while(error == true) {
			System.out.println("Veuillez renseignez l'id de la table à laquelle vous voulez affecter un serveur : ");
			Scanner s = new Scanner(System.in);
			long idTable = s.nextLong();
			if(tableDAO.tableExists(idTable)) {
				System.out.println("Veuillez renseignez l'id du serveur à affecter : ");
				long idServeur = s.nextLong();
				if(serveurDAO.serveurExists(idServeur)) {
					tableDAO.assignServeur(idServeur, idTable);
					error = false;
					System.out.println("Le serveur n°" + idServeur + " a bien été assigné à la table n°" + idTable);
				} else {
					System.out.println("L'id du serveur renseigné n'existe pas.");
				}
			} else {
				System.out.println("L'id de la table renseignée n'existe pas.");
			}
		}
	}

	public void printOptions() {
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");
		
		System.out.println("Assigner un serveur à une table (1)");
		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
	}
}
