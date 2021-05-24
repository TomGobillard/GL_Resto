package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.Impl.CommandeDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public class MenuCuisinier extends MenuCommun {
	
	public MenuCuisinier(boolean connected, Personnel user) {
		super(connected, user);
		// TODO Auto-generated constructor stub
	}

	public void printMenuCuisinier() {
		System.out.println("--------------------------------------------------");
		System.out.println("BIENVENUE DANS L'APPLICATION DE VOTRE RESTAURANT !");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Vous êtes connecté en tant que cuisinier");

		
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
					creerPlat();
					break;
				case 3:
					getCommandesEntrantes();
					break;
				case 4:
					setEtatPlatPret();
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

	private void setEtatPlatPret() {
		getCommandesEntrantes();
		PlatDAO platDAO = new PlatDAOImpl();
		platDAO.setEtatPlatPret();
	}

	public void printOptions() {
		
		System.out.println("--------------------------------------------------");
		System.out.println("Que souhaitez-vous faire ?\n");
		
		System.out.println("Consulter les stocks (1)");
		System.out.println("Créer un plat (2)");
		System.out.println("Visualiser les commandes entrantes (3)");
		System.out.println("Marquer un plat comme prêt (4)");
		
		System.out.println("Se déconnecter (20)");
		System.out.println("Quitter (21)");
		System.out.println("--------------------------------------------------");
		
	}
	
	private void getCommandesEntrantes() {
		CommandeDAOImpl cmdeDAO = new CommandeDAOImpl();
		System.out.println(cmdeDAO.getCommandeEntrantes());
	}

		
	public void creerPlat() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();
		PlatDAO platDAO = new PlatDAOImpl();
		CategoriePlatDAO categPlatDAO = new CategoriePlatDAOImpl();

		ArrayList<Produit> produits =  produitDAO.getProduitsDispos();

		ArrayList<CategoriePlat> listCateg = new ArrayList<>();
		listCateg = categPlatDAO.getAllCateg();

		for(CategoriePlat categ : listCateg) {
			System.out.println(categ.getId() + "; " + categ.getLibelle());
		}

		System.out.println("Dans quelle catégorie est votre plat ?");

		boolean error = true;

		int idCateg = ScanEntree.readIntegerWithDelimitations(0, listCateg.size());

		idCateg -= 1;

		System.out.println(listCateg.get(idCateg).getLibelle());
		System.out.println();

		error = true;

		System.out.println("Nom du plat : ");
		Scanner scNom = new Scanner(System.in);
		String nomPlat = scNom.nextLine();

		System.out.println("Prix : ");
		
		//int prix = ScanEntree.readIntegerWithDelimitations(0, 50);
		double prix = ScanEntree.readDoubleWithDelimitations(0, 50);

		ArrayList<Produit> compoPlat = new ArrayList<Produit>();
		int ajouter = 0;

		do {
			consulterProduitsDispos();

			System.out.println("Choisir un ingrédient à ajouter à la recette (par son Id)");

			error = true;
			int idIngredient = ScanEntree.readIntegerWithDelimitations(0, produits.size()-1);

			Produit ingredient = produits.get(idIngredient);

			System.out.println("Quantité : ");

			error = true;
			int qte = 0;

			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					qte = sc.nextInt();

					if(! produitDAO.isDispo(ingredient.getId(), qte)) {
						System.out.println("Le stock est isuffisant pour " + ingredient.getLibelle());
						System.out.println("Quantité : ");
					} else {
						error = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur numérique");
				}
			}

			ingredient.setQuantite(qte);
			compoPlat.add(ingredient);

			System.out.println("Composition actuelle du plat : ");

			for(Produit produitCompo : compoPlat) {
				System.out.println(produitCompo.getLibelle() + " : " + produitCompo.getQuantite());
			}

			System.out.println("Voulez-vous ajouter un autre ingrédient ? (1 : oui, 2 : non)");

			error = true;

			while(error == true) {
				try {
					Scanner sc = new Scanner(System.in);
					ajouter = sc.nextInt();

					error = false;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Il faut une valeur numérique");
				}
			}


		} while(ajouter == 1);

		platDAO.creerPlat(nomPlat, prix, compoPlat, idCateg+1);

	}


}
