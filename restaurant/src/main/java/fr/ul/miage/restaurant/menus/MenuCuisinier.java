package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;
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
		CommandeDAO cmdeDAO = new CommandeDAOImpl();
		String cmdesEntrantes = cmdeDAO.getCommandeEntrantes();
		if(cmdesEntrantes.equals("")) {
			System.out.println("Il n'y a pas de commande entrante pour le moment.");
		} else {
			System.out.println(cmdesEntrantes);
			if(toSetPlat) {			
				PlatDAO platDAO = new PlatDAOImpl();
				boolean error = true;
				while (error) {
					System.out.println("Saisissez le numéro de la commande concernée par la mise à jour du plat : ");
					long idCmde = ScanEntree.readLong();
					if (cmdeDAO.cmdeEntranteExists(idCmde)) {
						System.out.println("Commande n°" + idCmde + " sélectionnée.");
						cmdeDAO.showPlatCommande(idCmde);
						boolean platError = true;
						while (platError) {
							System.out.println("Sélectionnez maintenant l'id du plat : ");
							long idPlat = ScanEntree.readLong();
							if (platDAO.isPlatEnPreparation(idPlat, idCmde)) {
								platError = false;
								error = false;
								
								platDAO.setEtatPlatPret(idPlat, idCmde);
								System.out.println("Le plat est maintenant prêt !");

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
		ProduitDAO produitDAO = new ProduitDAOImpl();
		PlatDAO platDAO = new PlatDAOImpl();
		CategoriePlatDAO categPlatDAO = new CategoriePlatDAOImpl();
		ArrayList<Produit> produits =  produitDAO.getProduitsDispos();

		ArrayList<CategoriePlat> listCateg = new ArrayList<>();
		listCateg = categPlatDAO.getAllCateg();
		listCateg.forEach(ctg -> System.out.println(ctg.getId() + ". " + ctg.getLibelle()));

		System.out.println("Dans quelle catégorie est votre plat ?");
		int idCateg = ScanEntree.readIntegerWithDelimitations(0, listCateg.size()) -1;
		System.out.println(listCateg.get(idCateg).getLibelle() + "\n");

		System.out.println("Nom du plat : ");
		String nomPlat = ScanEntree.readString(); 

		System.out.println("Prix : ");
		double prix = ScanEntree.readDoubleWithDelimitations(0, 500);

		ArrayList<Produit> compoPlat = new ArrayList<Produit>();
		int ajouter = 0;

		do {
			consulterProduitsDispos();

			System.out.println("Veuillez renseignez l'id de l'ingrédient à ajouter à la recette :");

			int idIngredient = ScanEntree.readIntegerWithDelimitations(0, produits.size()-1);
			Produit ingredient = produits.get(idIngredient);

			System.out.println("Quantité (max 5) : ");

			boolean error = true;
			int qte = 0;

			while(error) {
				try {
					qte = ScanEntree.readInteger();

					if((!produitDAO.isDispo(ingredient.getId(), qte)) || (qte > 5 || qte < 0)) {
						System.out.println("Le stock est insuffisant pour " + ingredient.getLibelle());
						System.out.println("Quantité (max 5) : ");
					} else {
						error = false;
					}
				} catch (Exception e) {
					System.out.println("Il faut une valeur numérique");
				}
			}

			ingredient.setQuantite(qte);
			compoPlat.add(ingredient);

			System.out.println("Composition actuelle du plat : ");
			compoPlat.forEach(ingr -> System.out.println(ingr.getLibelle() + " : " + ingr.getQuantite()));

			System.out.println("Voulez-vous ajouter un autre ingrédient ? Oui (1) Non (2)");

			ajouter = ScanEntree.readIntegerWithDelimitations(1, 2);

		} while(ajouter == 1);

		creerPlatAction(nomPlat, prix, compoPlat, idCateg+1, platDAO);
	}
	
	private void creerPlatAction(String nomPlat, double prix, ArrayList<Produit> compoPlat, int idCateg, PlatDAO platDAO) {
		platDAO.creerPlat(nomPlat, prix, compoPlat, idCateg);	
	}


}
