package fr.ul.miage.restaurant.menus;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.Impl.CommandeDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.dao.CommandeDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public class MenuCuisinier extends MenuCommun {
	
	public MenuCuisinier(boolean connected, Personnel user) {
		super(connected, user);
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
				c2 = ScanEntree.readInteger();
				
				switch (c2) {
				case 1:
					consulterStocks();
					break;
				case 2:
					creerPlat();
					break;
				case 3:
					getCommandesEntrantes(false);
					break;
				case 4:
					getCommandesEntrantes(true);
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
		System.out.println();
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
	
	private void getCommandesEntrantes(boolean toSetPlat) {
		CommandeDAOImpl cmdeDAO = new CommandeDAOImpl();
		String cmdesEntrantes = cmdeDAO.getCommandeEntrantes();
		if(cmdesEntrantes.equals("")) {
			System.out.println("Il n'y a pas de commandes entrantes pour le moment.");
		} else {
			System.out.println(cmdesEntrantes);
			if(toSetPlat) {			
				PlatDAO platDAO = new PlatDAOImpl();
				
				Scanner s = new Scanner(System.in);
				boolean error = true;
				while (error) {
					System.out.println("Saisissez le numéro de la commande concernée par la mise à jour du plat : ");
					long idCmde = s.nextLong();
					if (cmdeDAO.cmdeEntranteExists(idCmde)) {
						System.out.println("Commande n°" + idCmde + " sélectionnée.");
						cmdeDAO.showPlatCommande(idCmde);
						boolean platError = true;
						while (platError) {
							System.out.println("Sélectionnez maintenant l'id du plat : ");
							long idPlat = s.nextLong();
							if (platDAO.isPlatEnPreparation(idPlat, idCmde)) {
								platError = false;
								error = false;
								
								platDAO.setEtatPlatPret(idPlat, idCmde);

							} else {
								System.out.println("L'id du plat renseigné n'existe pas.");
							}
						}
					} else {
						System.out.println("L'id de la commande renseignée n'existe pas.");
					}
				}
				
			}
		}
		
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
		int idCateg = ScanEntree.readIntegerWithDelimitations(0, listCateg.size()) -1;
		System.out.println(listCateg.get(idCateg).getLibelle() + "\n");

		System.out.println("Nom du plat : ");
		String nomPlat = ScanEntree.readString(); 

		System.out.println("Prix : ");
		double prix = ScanEntree.readDoubleWithDelimitations(0, 50);

		ArrayList<Produit> compoPlat = new ArrayList<Produit>();
		int ajouter = 0;

		do {
			consulterProduitsDispos();

			System.out.println("Choisir un ingrédient à ajouter à la recette (par son Id)");

			int idIngredient = ScanEntree.readIntegerWithDelimitations(0, produits.size()-1);
			Produit ingredient = produits.get(idIngredient);

			System.out.println("Quantité : ");

			boolean error = true;
			int qte = 0;

			while(error) {
				try {
//					Scanner sc = new Scanner(System.in);
//					qte = sc.nextInt();
					qte = ScanEntree.readInteger();

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

			ajouter = ScanEntree.readIntegerWithDelimitations(1, 2);

		} while(ajouter == 1);

		creerPlatAction(nomPlat, prix, compoPlat, idCateg+1, platDAO);
	}
	
	private void creerPlatAction(String nomPlat, double prix, ArrayList<Produit> compoPlat, int idCateg, PlatDAO platDAO) {
		platDAO.creerPlat(nomPlat, prix, compoPlat, idCateg);	
	}


}
