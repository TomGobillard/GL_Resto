package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.FactureDAOImpl;
import fr.ul.miage.restaurant.Impl.ServeurDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.FactureDAO;
import fr.ul.miage.restaurant.dao.ServeurDAO;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.models.Facture;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.models.Table;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public class MenuMaitreHotel extends MenuCommun {

	public MenuMaitreHotel(boolean connected, Personnel user) {
		super(connected, user);
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

				c2 = ScanEntree.readInteger();
				switch (c2) {
				case 1:
					assignerServeurATable();
					break;
				case 2:
					editerFacture();
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
		} while (connected);
	}

	private void editerFacture() {
		FactureDAO factureDAO = new FactureDAOImpl();
		TableDAO tableDAO = new TableDAOImpl();
		ArrayList<Table> listTables = tableDAO.getTableRepasFini();
		int idtable;
		if(listTables.size() > 0) {
			do {
	
				System.out.println("Veuillez sélectionner la table pour laquelle vous voulez éditer une facture :");
	
				listTables.forEach(table -> System.out.println(table));
	
				idtable = ScanEntree.readInteger();
				
	
			} while (!tableDAO.tableExists(idtable));
			
			
			Table table = tableDAO.find(idtable);
			System.out.println("Sélectionnez le type de repas pour la facture :");
			System.out.println("DEJEUNER (1)");
			System.out.println("DINER (2)");
	
			int repasNum = ScanEntree.readIntegerWithDelimitations(1, 2);
			String repas;
			if (repasNum == 1) {
				repas = "DEJEUNER";
			} else {
				repas = "DINER";
			}
	
			Facture facture = factureDAO.genererFacture(table.getIdClient(), repas);
	
			System.out.println("Votre facture a bien été générée :");
			System.out.println(facture);
		} else {
			System.out.println("Désolé, il n'y a aucune table prête à être facturée.");
		}
	}

	private void assignerServeurATable() {
		TableDAO tableDAO = new TableDAOImpl();
		ServeurDAO serveurDAO = new ServeurDAOImpl();
		Scanner sc = new Scanner(System.in, "UTF-8");
		
		boolean error = true;

		while (error) {
				ArrayList<Table> tables = tableDAO.getAll();
				
				for (Table table : tables) {
					System.out.println("Table " + table.getId());
				}
				
				long idTable = ScanEntree.readIdTable(tables, "à laquelle vous souhaitez affecter un serveur :");
				System.out.println("Veuillez renseignez l'id du serveur à affecter : ");
				
				ArrayList<Serveur> listServeur = serveurDAO.getAll();
				
				for (int i=0; i < listServeur.size(); i++) {
					System.out.println(i + ". " + listServeur.get(i).getNom() + " " + listServeur.get(i).getPrenom() + " idBdD : "
							+ listServeur.get(i).getId());
				}
				
				int intIdServeur = ScanEntree.readIntegerWithDelimitations(0, listServeur.size()-1);
				long idServeur = listServeur.get(intIdServeur).getId();
				
				if (serveurDAO.serveurExists(idServeur)) {
					tableDAO.assignServeur(idServeur, idTable);
					error = false;
					System.out.println("Le serveur n°" + idServeur + " a bien été assigné à la table n°" + idTable);
				} else {
					System.out.println("L'id du serveur renseigné n'existe pas.");
				}
				
		}
	}

	public void printOptions() {
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");

		System.out.println("Assigner un serveur à une table (1)");
		System.out.println("Editer une facture (2)");

		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
	}
}
