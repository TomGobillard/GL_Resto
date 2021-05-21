package fr.ul.miage.restaurant.menus;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.CommandeDAOImpl;
import fr.ul.miage.restaurant.Impl.FactureDAOImpl;
import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.CommandeDAO;
import fr.ul.miage.restaurant.dao.FactureDAO;
import fr.ul.miage.restaurant.dao.PersonnelDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public class MenuDirecteur extends MenuCommun {

	PlatDAO platDAO;
	PersonnelDAO personnelDAO;
	CommandeDAO commandeDAO;
	FactureDAO factureDAO;

	public MenuDirecteur(boolean connected, Personnel user) {
		super(connected, user);
		// TODO Auto-generated constructor stub
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

				Scanner s = new Scanner(System.in);
				c2 = s.nextInt();
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
		System.out.println("Mettre à jour les stocks (2)");
		System.out.println("Gérer la carte du jour (3)");
		System.out.println("Consulter la popularité des plats (4)");
		System.out.println("Gérer les employés (5)");
		System.out.println("Voir le temps moyen de préparation des commandes (6)");
		System.out.println("Voir le profit du déjeuner et du diner (7)");
		System.out.println("Voir la recette quotidienne, hebdomadaire et mensuelle (8)");

		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
	}

	public void MajStocks() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();	
		ArrayList<Produit> listProduits;

		int continuer = 0;

		do {
			listProduits =  produitDAO.listProduit();

			consulterStocks();

			System.out.println("Quel stock souhaitez vous mettre à jour : ");

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
					System.out.println("Il faut une valeur numérique");
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
					System.out.println("Il faut une valeur numérique");
				}
			}

			int newQte = listProduits.get(idProduit-1).getQuantite() + qte;

			Produit majProduit = new Produit(idProduit, listProduits.get(idProduit-1).getLibelle(), newQte);

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
					System.out.println("Il faut une valeur numérique");
				}
			}

		} while (continuer == 1);

	}

	public void printCarteduJour() {
		//PlatDAO platDAO = new PlatDAOImpl();
		ArrayList<Plat> carteduJour = platDAO.getCarteduJour();

		if(carteduJour.size() == 0) {
			System.out.println("Il n'y a auncun plat sur la carte du jour");
		} else {
			System.out.println("Voici la carte du jour \n");

			for(Plat plat : carteduJour) {
				System.out.println(plat.getLibelle());
			}
		}
	}

	public void ajoutPlatCarteduJour() {
		ArrayList<Plat> plats = new ArrayList<>();
		//plats = platDAO.platsCateg();
		plats = platDAO.platsDispoCateg();

		if(plats.size()>0) {

			for(int i=0; i<plats.size(); i++) {
				System.out.println(i + ": " + plats.get(i).getLibelle());
			}

			System.out.println();
			System.out.println("Sélectionnez un plat à ajouter (id du plat)");

			boolean error = true;
			int indexPlat = 0;

			while(error == true) {
				try {
					Scanner scPlat = new Scanner(System.in);
					indexPlat = scPlat.nextInt();
					if(indexPlat >= 0 && indexPlat < plats.size()) {
						error = false;
					} else {
						System.out.println("Le plat sélectionné n'existe pas");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur numérique");
				}
			}

			Plat plat = plats.get(indexPlat);

			platDAO.ajoutPlatCarteduJour(plat.getId());
		}

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
			Scanner s = new Scanner(System.in);
			c2 = s.nextInt();
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
		
		System.out.println("Voici les 5 plats les plus populaires de votre restaurant \n");

		if(listPlats.size()>0) {		
			for(int i=0; i < listPlats.size(); i++) {
				if(i==5)
					break;
				Plat plat = listPlats.get(i);
				double CA = plat.getNbCommandes() * plat.getPrix();
				System.out.println(plat.getLibelle() + " - Popularité : " + plat.getNbCommandes() + " - Revenus : " + CA + "€");
			}
		}
		
		System.out.println();
		System.out.println("Voulez-vous voir tous les plats ? (1.Oui 2.Non)");
		
		int choix  = ScanEntree.readIntegerWithDelimitations(1, 2);
		
		if(choix == 1) {
			checkPopulariteAllPlats();
		}
	}
	
	public void checkPopulariteAllPlats() {
		ArrayList<Plat> listPlats = platDAO.platsPopulaires();

		if(listPlats.size()>0) {			
			for(Plat plat : listPlats) {
				System.out.println(plat.getLibelle() + " - Popularité : " + plat.getNbCommandes());
			}
		}
	}
	
	public void gererEmploye() {
		
		suiviEmploye();
	}
	
	public void suiviEmploye() {
		ArrayList<Personnel> listPersonnel = personnelDAO.getAll();
		Personnel personnel;
		
		for(int i=0; i<listPersonnel.size(); i++) {
			personnel = listPersonnel.get(i);
			System.out.println(personnel.toString());
		}
	}

	public void gererEmployes() {
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");

		System.out.println("Lister les employés (1)");
		System.out.println("Créer un employé (2)");
		System.out.println("Modifier un employé (3)");

		System.out.println("Retour (10)");
		System.out.println("--------------------------------------------------");

		int c2;
		do {
			Scanner s = new Scanner(System.in);
			c2 = s.nextInt();
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

		for(Personnel personnel : listPersonnel) {
			System.out.println(personnel.toString());
		}
	}

	public void creerEmploye() {
		System.out.println("Création d'un nouvel employé");
		Scanner sc = new Scanner(System.in);

		System.out.println("Nom : ");
		String nom = sc.next();

		System.out.println("Prénom : ");
		String prenom = sc.next();
		
		System.out.println("Login : ");
		String login = sc.next();
		
		System.out.println("Mdp : ");
		String mdp = sc.next();

		String role = selectRole();
		
		Personnel personnel = new Personnel(role, login, mdp, nom, prenom);
		
		personnelDAO.create(personnel);
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
		
		boolean error = true;

		int intSelect = 0;

		while(error == true) {
			try {
				Scanner sc = new Scanner(System.in);
				intSelect = sc.nextInt();
				if(intSelect >= 0 && intSelect <= 5) {
					error = false;
				} else {
					System.out.println("Choix hors limites");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique");
			}
		}

		switch (intSelect) {
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
		System.out.println("Séléctionnez un employé à modifier : ");
		
		ArrayList<Personnel> listPersonnel = personnelDAO.getAll();
		
		for (int i = 0; i < listPersonnel.size(); i++) {
			Personnel personnel = listPersonnel.get(i);
			System.out.println(i + ": " + personnel.getNom() + " " + personnel.getPrenom());
		}
		
		System.out.println("\nId : ");
		
		boolean error = true;

		int intSelect = 0;

		while(error == true) {
			try {
				Scanner sc = new Scanner(System.in);
				intSelect = sc.nextInt();
				if(intSelect >= 0 && intSelect < listPersonnel.size()) {
					error = false;
				} else {
					System.out.println("Choix hors limites");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique");
			}
		}
		
		int idPersonnel = (int) listPersonnel.get(intSelect).getId();
		modifEmploye(idPersonnel);
	}
	
	public void modifEmploye(int idPersonnel) {
		System.out.println("Quel attribut voulez-vous modifier ?");
		
		System.out.println("1 : Login");
		
		boolean error = true;

		int intSelect = 0;

		while(error == true) {
			try {
				Scanner sc = new Scanner(System.in);
				intSelect = sc.nextInt();
				if(intSelect >= 0 && intSelect <= 1) {
					error = false;
				} else {
					System.out.println("Choix hors limites");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Il faut une valeur numérique");
			}
		}
		
		switch (intSelect) {
		case 1:
			updateLoginPersonnel(idPersonnel);
			break;

		default:
			break;
		}
	}
	
	public void updateLoginPersonnel(int idPersonnel) {
		System.out.println("Veuillez rentrer le nouveau login :");
		
		Scanner sc = new Scanner(System.in);
		String newLogin = sc.next();
		
		Personnel personnel = (Personnel) personnelDAO.find((long)idPersonnel);
		
		personnel.setLogin(newLogin);
		
		personnelDAO.update(personnel);
	}

	public void statCommande() {
		Timestamp tempsMoyen = commandeDAO.getTempsCommandesFinies();
		java.util.Date date = new java.util.Date(tempsMoyen.getTime());
		SimpleDateFormat formatter= new SimpleDateFormat("HH");
		SimpleDateFormat formatter2= new SimpleDateFormat("mm");
		
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("Voici le temps moyen de préparation des commandes\n");
		System.out.println("Temps moyen de préparation : " + formatter.format(date) + "h" + formatter2.format(date));
		System.out.println();
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
		System.out.println("Recette hebdomadaire : " + recetteMois + "€");
		System.out.println("Recette mensuelle : " + recetteMois + "€");
		System.out.println();
	}
}
