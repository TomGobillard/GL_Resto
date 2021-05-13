package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.PersonnelDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;

public class MenuDirecteur extends MenuCommun {

	PlatDAO platDAO;
	PersonnelDAO<Personnel> personnelDAO;

	public MenuDirecteur(boolean connected, Personnel user) {
		super(connected, user);
		// TODO Auto-generated constructor stub
		platDAO = new PlatDAOImpl();
		personnelDAO = new PersonnelDAOImpl();
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
					gererEmploye();
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
		System.out.println("Gérer un employé (5)");

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
	
	public void checkPopularitePlat() {
		ArrayList<Plat> listPlats = platDAO.getAll();
		
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
	
	public void creerEmploye() {
		
	}

}
