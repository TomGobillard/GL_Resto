package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;

import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ClientDAOImpl;
import fr.ul.miage.restaurant.Impl.CommandeDAOImpl;
import fr.ul.miage.restaurant.Impl.CompositionCmdeDAOImpl;
import fr.ul.miage.restaurant.Impl.CompositionPlatDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.dao.ClientDAO;
import fr.ul.miage.restaurant.dao.CommandeDAO;
import fr.ul.miage.restaurant.dao.CompositionCmdeDAO;
import fr.ul.miage.restaurant.dao.CompositionPlatDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.models.Client;
import fr.ul.miage.restaurant.models.CompositionCmde;
import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Table;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public class MenuServeur extends MenuCommun {

	boolean tableInfoInitPrinted = false;
	TableDAO tableDAO = new TableDAOImpl(user);

	public MenuServeur(boolean connected, Personnel user) {
		super(connected, user);
	}
	
	public MenuServeur() {}

	public void printOptions() {
		if(!tableInfoInitPrinted) {
			printOccupationTablesWithAvancement();
		}
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");

		System.out.println("Consulter les stocks (1)");
		System.out.println("Consulter l'état d'occupation des tables (2)");
		System.out.println("Consulter les plats par catégorie (3)");
		System.out.println("Saisir une commande (4)");
		System.out.println("Consulter les plats à servir (5)");
		System.out.println("Consulter l'avancement du repas d'une table (6)");
		System.out.println("Consulter les informations d'une table (7)");
		System.out.println("Installer un client (8)");

		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
	}
	
	public void printMenuServeur() {
		System.out.println("--------------------------------------------------");
		System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Vous êtes connecté en tant que serveur");

		do {

			int c;
			do {

				printOptions();

				c = ScanEntree.readInteger();
				switch (c) {
				case 1:
					consulterStocks();
					break;
				case 2:
					printOccupation();
					break;
				case 3:
					listerPlatsSelonCateg();
					break;
				case 4:
					saisirCommande();
					break;
				case 5:
					consulterServices();
					break;
				case 6:
					consulterAvancementTable();
					break;
				case 7:
					showInfoTable();
					break;
				case 8:
					installerClient();
					break;
				case 20:
					deconnexion();
					tableInfoInitPrinted = false;
					break;
				case 21:
					System.out.println("Fermeture de l'application.");
					System.exit(0);
					break;
				default:
					System.out.println("Erreur de choix, réessayez.\n");
					break;
				}

			} while (c != 20);
		} while (connected);
	}

	private void printOccupation() {
		System.out.println("Etat de toutes les tables : \n");
		ArrayList<Table> tables = tableDAO.getAll();
		tables.forEach(table -> System.out.println("Table n°" + table.getId() + " : " + table.getEtat()));
		System.out.println();
	}

	private long initServeurTableIDForQuery(String msg) {
		ArrayList<Integer> tables = tableDAO.getServeurTablesId(user.getId());
		return ScanEntree.readId(tables, msg);
	}

	private void showInfoTable() {
		TableDAO tableDAO = new TableDAOImpl();
		ArrayList<Integer> tables = tableDAO.getServeurTablesId(user.getId());
		tables.forEach(table -> System.out.println("Table n°" + table));
		
		long idTable = initServeurTableIDForQuery("dont vous souhaitez connaitre les informations.");
		System.out.println(tableDAO.obtenirInfoTable(idTable));
	}

	private void consulterAvancementTable() {
		TableDAO tableDAO = new TableDAOImpl();
		ArrayList<Integer> tables = tableDAO.getServeurTablesId(user.getId());
		tables.forEach(table -> System.out.println("Table n°" + table));
		
		long idTable = initServeurTableIDForQuery("dont vous souhaitez connaitre l'avancement.");
		String avancement = tableDAO.showAvancement(idTable);
		System.out.println(avancement);
	}
	
	private void consulterServices() {
		CompositionCmdeDAO compoCmdeDAO = new CompositionCmdeDAOImpl();

		ArrayList<CompositionCmde> compoCmdes  = new ArrayList<>();

		compoCmdes = compoCmdeDAO.getCompoCmdesWithServeur(this.user.getId());
		
		if(compoCmdes.size()>0) {
			System.out.println("Sélectionnez maintenant l'id du plat : ");
			int idcmde = ScanEntree.readIntegerWithDelimitations(0, compoCmdes.size());

			long idPlat = compoCmdes.get(idcmde).getIdPlat();
			long idCommande = compoCmdes.get(idcmde).getIdCommande();
			compoCmdeDAO.setEtatPlatsServis(idCommande, idPlat);
			System.out.println("Le plat est servi.");
		}
		else {
			System.out.println("Aucun plat à servir.");
		}
		
	}

	public void saisirCommande() {
		ArrayList<Plat> plats = new ArrayList<>();

		long numTable = choisirTablePourCmde();

		if(numTable > 0) {

			int c;
			do {
				System.out.println("--------------------------------------------------");
				System.out.println("Que souhaitez-vous faire ?\n");

				System.out.println("Ajouter un plat à la commande (1)");
				System.out.println("Valider la commande (2)");
				System.out.println("Annuler la commande (10)");
				System.out.println("--------------------------------------------------");
				
				c = ScanEntree.readInteger();
				switch (c) {
				case 1:
					Plat plat = ajoutPlatCommande();
					if(plat!=null)
						plats.add(plat);
					break;
				case 2:
					System.out.println("Voici la commande :\n");
					for(Plat p : plats) {
						System.out.println(p);
					}
					if(!plats.isEmpty()) {
						validerCommande(plats,(int) numTable);
					} else {
						System.out.println("La commande est vide.");
						c++;
					}
					break;

				case 10:
						System.out.println("Commande annulée.");
					break;

				default:
					System.out.println("Erreur de choix, réessayez.\n");
					break;
				}
			} while (c != 2 && c != 10);

		}
	} 

	private long choisirTablePourCmde() {
		TableDAO tableDAO = new TableDAOImpl();
		ArrayList<Integer> tables = tableDAO.getServeurTablesId(user.getId());
		if(!tables.isEmpty()) {
			tables.forEach(table -> System.out.println("Table n°" + table));
			System.out.println();
			
			return initServeurTableIDForQuery("pour laquelle vous souhaitez saisir une commande.");
		} else {
			System.out.println("Il n'y a pas de table prête à accueillir une commande.");
			return -1;
		}


	}

	public void validerCommande(ArrayList<Plat> plats, int numTable) {

		CommandeDAO commandeDAO = new CommandeDAOImpl();
		commandeDAO.creerCommande(numTable);

		int idCommande = commandeDAO.getLastCommande();

		for (Plat p : plats) {
			commandeDAO.creerCompositionCmde(idCommande, (int) p.getId());
		}

		// incrémentation nombre de commandes d'un plat
		PlatDAO platDAO = new PlatDAOImpl();
		platDAO.incrementeNbCommandes(plats);

		//mise à jour des stocks de matières premières
		CompositionPlatDAO compositionPlatDAO = new CompositionPlatDAOImpl();
		ArrayList<CompositionPlat> compoPlats = new ArrayList<>();
		compoPlats = compositionPlatDAO.getWithPlats(plats);

		ProduitDAO produitDAO = new ProduitDAOImpl();
		for(CompositionPlat cp : compoPlats) {
			produitDAO.updateQuantite(cp);
		}

		System.out.println("La commande a été saisie.");
	}

	public Plat ajoutPlatCommande() {
		PlatDAO platDAO = new PlatDAOImpl();
		CategoriePlatDAO categoriePlatDAO = new CategoriePlatDAOImpl();
		ArrayList<CategoriePlat> listcategPlat = categoriePlatDAO.getAll();

		listcategPlat.forEach(ctg -> System.out.println(ctg.getId() + ". " + ctg.getLibelle()));

		System.out.println();
		System.out.println("Sélectionnez la catégorie : \n");

		int intIdCateg = ScanEntree.readIntegerWithDelimitations(1, listcategPlat.size());
		CategoriePlat categ = listcategPlat.get(intIdCateg-1);

		ArrayList<Plat> listPlatsCateg = platDAO.listerPlatSelonCategorie(categ.getId());

		if(listPlatsCateg.isEmpty())
			System.out.println("Il n'y a aucun plat dans cette catégorie");
		else {
			for(Plat plat : listPlatsCateg) {
				System.out.println(plat.getId() + ". " + plat.getLibelle());
			}

			Long idPlat = ScanEntree.readIdPlat(listPlatsCateg, " à ajouter à la commande");
			
			return platDAO.find(idPlat);
		}
		return null;
	}

	private void printOccupationTablesWithAvancement() {
		System.out.println("Etat de vos tables : \n");
		ArrayList<Table> tables = tableDAO.getServeurTables(user.getId());
		tables.forEach(table -> System.out.println("Table n°" + table.getId() + " : " + table.getAvancement()));
		System.out.println();
		tableInfoInitPrinted = true;
	}

	private void installerClient() {
		ArrayList<Integer> listTables = tableDAO.getServeurTablesLibres(user.getId());
		listTables.forEach(table -> System.out.println("Table n°" + table));
		long idTable = ScanEntree.readId(listTables, "ou vous souhaitez installer le client");
		installerClientAction(idTable);
		System.out.println("Le client a bien été installé.");
	}
	
	public void installerClientAction(long numTable) {
		ClientDAO clientDAO = new ClientDAOImpl();
		Client client = clientDAO.create(null);	
		tableDAO.installerClient(client.getId(), numTable);
		
	}
}
