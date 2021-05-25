package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Table;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public class MenuAssistantService extends MenuCommun{
	
	public MenuAssistantService(boolean connected, Personnel user) {
		super(connected, user);
		// TODO Auto-generated constructor stub
	}

	static Personnel user;
	
	public void printMenuAssistantService() {
		System.out.println("--------------------------------------------------");
		System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Vous êtes connecté en tant qu'assistant de service");

		
		do {

			int c2;
			do {

				printOptions();

				Scanner s = new Scanner(System.in);
				c2 = s.nextInt();
				switch (c2) {
				case 1:
					getInfoTable();
					break;
				case 2:
					dresserTable();
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
		
		System.out.println("Obtenir les informations d'une table (1)");
		System.out.println("Débarasser et dresser une table (2)");
		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
	}

	private void dresserTable() {
		TableDAO tableDAO = new TableDAOImpl();
	
		ArrayList<Table> tables = tableDAO.getTablesADresserOuRanger();
		tables.forEach(table -> System.out.println(table));
		long idTable = ScanEntree.readIdTable(tables, "que vous souhaitez débarasser et dresser : ");
		dresserTableAction(idTable);
		System.out.println("La table n°" + idTable + " à bien été installée.");
	}
	

	private void dresserTableAction(long idTable) {
		TableDAO tableDAO = new TableDAOImpl();
		tableDAO.dresserTable(idTable);
	}

	public void getInfoTable() {
		TableDAO tableDAO = new TableDAOImpl();
		ArrayList<Table> tables = tableDAO.getAll();
		tables.forEach(table -> System.out.println(table));
		long idTable = ScanEntree.readIdTable(tables, "dont vous souhaîtez connaitre les informations :");
		tableDAO.obtenirInfoTable(idTable);
	}
}
