package fr.ul.miage.restaurant.menus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ClientDAOImpl;
import fr.ul.miage.restaurant.Impl.CommandeDAOImpl;
import fr.ul.miage.restaurant.Impl.FactureDAOImpl;
import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.dao.ClientDAO;
import fr.ul.miage.restaurant.dao.CommandeDAO;
import fr.ul.miage.restaurant.dao.FactureDAO;
import fr.ul.miage.restaurant.dao.PersonnelDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public class MenuDirecteur extends MenuCommun {

	PlatDAO platDAO;
	PersonnelDAO<Personnel> personnelDAO;
	CommandeDAO commandeDAO;
	FactureDAO factureDAO;

	public MenuDirecteur(boolean connected, Personnel user) {
		super(connected, user);
		platDAO = new PlatDAOImpl();
		personnelDAO = new PersonnelDAOImpl();
		commandeDAO = new CommandeDAOImpl();
		factureDAO = new FactureDAOImpl();
	}

	public void printMenuDirecteur() {
		System.out.println("--------------------------------------------------");
		System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Vous êtes connecté en tant que directeur");

		do {

			int c2;
			do {

				printOptions();

				c2 = ScanEntree.readInteger();
				switch (c2) {
				case 1:
					consulterStocks();
					break;
				case 2:
					MajStocks();
					break;
				case 3:
					majCarteduJour();
					break;
				case 4:
					checkPopularitePlat();
					break;
				case 5:
					gererEmployes();
					break;
				case 6:
					statCommande();
					break;
				case 7 :
					profitDejeunerDiner();
					break;					
				case 8 :
					recetteJourSemaineMois();
					break;
				case 9:
					calculateRoationTimeAvg();
					break;
					
				case 10:
					creerProduit();
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

	private void calculateRoationTimeAvg() {
		ClientDAO clientDAO = new ClientDAOImpl();
		java.util.Date date = new java.util.Date(clientDAO.getRotationTimeAvg().getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("HH");
		SimpleDateFormat formatter2 = new SimpleDateFormat("mm");
		SimpleDateFormat formatter3 = new SimpleDateFormat("ss");
		String msg = "Le temps moyen de rotation des clients est de ";
		if(!formatter.format(date).equals("00")) {
			msg += formatter.format(date) + "h ";
		}
		msg += formatter2.format(date) + "mn " + formatter3.format(date) + "s";
		System.out.println(msg);
	}

	public void printOptions() {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");

		System.out.println("Consulter les stocks (1)");
		System.out.println("Mettre à jour les stocks (2)");
		System.out.println("Gérer la carte du jour (3)");
		System.out.println("Consulter la popularité des plats (4)");
		System.out.println("Gérer les employés (5)");
		System.out.println("Voir le temps moyen de préparation des commandes (6)");
		System.out.println("Voir le profit du déjeuner et du diner (7)");
		System.out.println("Voir la recette quotidienne, hebdomadaire et mensuelle (8)");
		System.out.println("Consulter le temps moyen de rotation des clients (9)");
		System.out.println("Ajouter un nouveau produit (10)");

		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
	}

	public void MajStocks() {
		ProduitDAO produitDAO = new ProduitDAOImpl();	
		ArrayList<Produit> listProduits;

		int continuer = 0;

		do {
			listProduits =  produitDAO.listProduit();
			consulterStocks();

			System.out.println("Quel stock souhaitez vous mettre à jour : ");
			int idProduit = (int) ScanEntree.readIdProduit(listProduits, "dont vous souhaitez mettre à jour le stock");

			System.out.println("Quantité à ajouter (max: 100)");
			int qte = ScanEntree.readIntegerWithDelimitations(0, 1000);
			int newQte = listProduits.get(idProduit-1).getQuantite() + qte;

			Produit majProduit = new Produit(idProduit, listProduits.get(idProduit-1).getLibelle(), newQte);
			produitDAO.update(majProduit);

			System.out.println("Voulez-vous continuer à mettre à jour les stocks ? (1 : oui, 2 : non)");
			continuer = ScanEntree.readIntegerWithDelimitations(1, 2);

		} while (continuer == 1);

	}

	public void printCarteduJour() {
		ArrayList<Plat> carteduJour = platDAO.getCarteduJour();

		if(carteduJour.isEmpty()) {
			System.out.println("Il n'y a auncun plat sur la carte du jour");
		} else {
			System.out.println("Voici la carte du jour \n");

			for(Plat plat : carteduJour) {
				System.out.println(plat.getLibelle());
			}
		}
	}

	public void ajoutPlatCarteduJour() {
		CategoriePlatDAO categoriePlatDAO = new CategoriePlatDAOImpl();
		PlatDAO platDAO = new PlatDAOImpl();

		ArrayList<CategoriePlat> listcategPlat = categoriePlatDAO.getAll();
		listcategPlat.forEach(ctg -> System.out.println(ctg.getId() + ". " + ctg.getLibelle()));

		System.out.println("\nSélectionnez la catégorie : \n");

		int intIdCateg = ScanEntree.readIntegerWithDelimitations(1, listcategPlat.size());
		CategoriePlat categ = listcategPlat.get(intIdCateg);

		ArrayList<Plat> listPlatsCateg = platDAO.listerPlatSelonCategorie(categ.getId());

		if(listPlatsCateg.isEmpty())
			System.out.println("Il n'y a aucun plat dans cette catégorie");
		else {
			listPlatsCateg.forEach(rec -> System.out.println(rec.getId() + ". " + rec.getLibelle()));
			long idPlat = ScanEntree.readIdPlat(listPlatsCateg, "à ajouter.");
			platDAO.ajoutPlatCarteduJour(idPlat);
		}
	}
	
	public void ajoutPlatCarteduJour_Action(long idPlat) {
		platDAO.ajoutPlatCarteduJour(idPlat);
	}

	public void majCarteduJour() {

		printCarteduJour();

		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");

		System.out.println("Ajouter un plat (1)");
		System.out.println("Réinitialiser la carte du jour (2)");

		System.out.println("Retour (10)");

		System.out.println("--------------------------------------------------");

		int c2;
		do {
			c2 = ScanEntree.readInteger();
			switch (c2) {
			case 1:
				ajoutPlatCarteduJour();
				break;

			case 2:
				platDAO.initCarteduJour();
				break;

			case 10:
				break;

			default:
				System.out.println("Erreur de choix, réessayez.\n");
				break;
			}

		} while (c2 != 1 && c2 != 2 && c2 != 10);
	}

	//Affiche les 5 plats les plus populaires
	public void checkPopularitePlat() {
		ArrayList<Plat> listPlats = platDAO.platsPopulaires();

		System.out.println("Voici les 5 plats les plus populaires de votre restaurant : \n");

		if(!listPlats.isEmpty()) {		
			for(int i=0; i < 5; i++) {
				Plat plat = listPlats.get(i);
				double CA = plat.getNbCommandes() * plat.getPrix();
				System.out.println("Plat : " + plat.getLibelle() + "\nPopularité : " + plat.getNbCommandes() + " commandes \nRevenus : " + CA + "€\n");
			}
		}

		System.out.println();
		System.out.println("Voulez-vous voir tous les plats ? Oui (1) Non (2)");

		int choix  = ScanEntree.readIntegerWithDelimitations(1, 2);

		if(choix == 1) {
			checkPopulariteAllPlats();
		}
	}

	public void checkPopulariteAllPlats() {
		ArrayList<Plat> listPlats = platDAO.platsPopulaires();

		if(!listPlats.isEmpty()) {			
			for(Plat plat : listPlats) {
				System.out.println("Plat : " + plat.getLibelle() + "\nPopularité : " + plat.getNbCommandes() + " commandes\n");
			}
		}
	}

	public void suiviEmploye() {
		ArrayList<Personnel> listPersonnel = personnelDAO.getAll();
		listPersonnel.forEach(personnel -> System.out.println(personnel));
	}

	public void gererEmployes() {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");

		System.out.println("Lister les employés (1)");
		System.out.println("Créer un employé (2)");
		System.out.println("Modifier un employé (3)");

		System.out.println("Retour (10)");
		System.out.println("--------------------------------------------------");

		int c2;
		do {

			c2 = ScanEntree.readInteger();
			switch (c2) {
			case 1:
				listerEmployes();
				break;
			case 2:
				creerEmploye();
				break;
			case 3:
				modifierEmployes();
				break;
			case 10:
				break;
			default:
				System.out.println("Erreur de choix, réessayez.\n");
				break;
			}

		} while (c2 != 1 && c2 != 2 && c2 != 3 && c2 != 10);
	}

	public void listerEmployes() {
		System.out.println("Voici la liste des employés : ");

		ArrayList<Personnel> listPersonnel = personnelDAO.getAll();
		listPersonnel.forEach(personnel -> System.out.println(personnel));
	}

	public void creerEmploye() {
		System.out.println("Création d'un nouvel employé");

		System.out.println("Nom : ");
		String nom = ScanEntree.readString();

		System.out.println("Prénom : ");
		String prenom = ScanEntree.readString();

		System.out.println("Login : ");
		String login = ScanEntree.readString();

		System.out.println("Mdp : ");
		String mdp = ScanEntree.readString();

		personnelDAO.create(new Personnel(selectRole(), login, mdp, nom, prenom));
		System.out.println("L'employé à bien été ajouté au personnel.");
	}

	public String selectRole() {
		System.out.println("\nRoles : ");
		System.out.println("1: Maître d'hôtel");
		System.out.println("2: Serveur");
		System.out.println("3: Assistant de service");
		System.out.println("4: Cuisinier");
		System.out.println("5: Directeur");

		String role = null;

		System.out.println("Veuillez choisir un rôle");

		int roleId = ScanEntree.readIntegerWithDelimitations(1, 5);

		switch (roleId) {
		case 1:
			role = "MAITRE HOTEL";
			break;

		case 2:
			role = "SERVEUR";
			break;

		case 3:
			role = "ASSISTANT DE SERVICE";
			break;

		case 4:
			role = "CUISINIER";
			break;

		case 5:
			role = "DIRECTEUR";
			break;
		}

		return role;
	}

	public void modifierEmployes() {
		ArrayList<Personnel> listPersonnel = personnelDAO.getAll();
		listPersonnel.forEach(personnel -> System.out.println("Personnel n°" + personnel.getId() + " : " + personnel.getNom() + " " + personnel.getPrenom()));
		int idPersonnel = (int) ScanEntree.readIdPersonnel(listPersonnel, "à modifier :");
		
		modifEmploye(idPersonnel);
	}

	public void modifEmploye(int idPersonnel) {
		System.out.println("Quel attribut voulez-vous modifier ?");

		System.out.println("1 : Login");

		int idAttribut = -1;

		idAttribut = ScanEntree.readIntegerWithDelimitations(1, 1);
		switch (idAttribut) {
		case 1:
			updateLoginPersonnel(idPersonnel);
			break;
		default:
			break;
		}
	}

	public void updateLoginPersonnel(int idPersonnel) {
		System.out.println("Veuillez rentrer le nouveau login :");

		String newLogin = ScanEntree.readString();

		Personnel personnel = personnelDAO.find((long)idPersonnel);

		personnel.setLogin(newLogin);

		personnelDAO.update(personnel);
	}

	public void statCommande() {
		Timestamp tempsMoyen = commandeDAO.getTempsCommandesFinies();
		try {
			java.util.Date date = new java.util.Date(tempsMoyen.getTime());
			SimpleDateFormat formatter= new SimpleDateFormat("HH");
			SimpleDateFormat formatter2= new SimpleDateFormat("mm");
	
			System.out.println();
			System.out.println("--------------------------------------------------");
			System.out.println("Voici le temps moyen de préparation des commandes\n");
			System.out.println("Temps moyen de préparation : " + formatter.format(date) + "h" + formatter2.format(date) + "mn");
		} catch (NullPointerException e) {
			System.out.println("Il n'y a aucune commande qui ait été terminée pour l'instant.");
		}
	}

	public void profitDejeunerDiner() {
		int profitDejeuner = factureDAO.profitDejeuner();
		int profitDiner = factureDAO.profitDiner();

		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("Voici les profits du jour : ");
		System.out.println("Déjeuner : " + profitDejeuner + "€");
		System.out.println("Diner : " + profitDiner + "€");
		System.out.println();
	}

	public void recetteJourSemaineMois() {
		int recetteJour = factureDAO.getRecetteQuotidienne();
		int recetteSemaine = factureDAO.getRecetteHebdo();
		int recetteMois = factureDAO.getRecetteMensuelle();

		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("Voici les recettes : ");
		System.out.println("Recette quotidienne : " + recetteJour + "€");
		System.out.println("Recette hebdomadaire : " + recetteSemaine + "€");
		System.out.println("Recette mensuelle : " + recetteMois + "€");
		System.out.println();
	}
	
	public void creerProduit() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();
		
		System.out.println("Vous allez créer un nouveau produit");
		
		System.out.println("Nom du produit : ");
		
		String nom = ScanEntree.readString();
		
		System.out.println("Quantité initiale (entre 50 et 500) : ");
		
		int qteInit = ScanEntree.readIntegerWithDelimitations(50, 500);
		
		Produit newProduit = new Produit(0, nom, qteInit);
		
		Produit existProduit = produitDAO.findByName(nom);
		
		if((existProduit.getLibelle() == null) || (existProduit == null))		
			produitDAO.create(newProduit);
		else {
			System.out.println("Ce produit existe déjà");
		}
	}
}
