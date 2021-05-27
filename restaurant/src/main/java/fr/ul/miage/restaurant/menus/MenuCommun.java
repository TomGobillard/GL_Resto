package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;

import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.systeme.ScanEntree;

public abstract class MenuCommun {
	boolean connected=false;
	Personnel user;

	public MenuCommun(boolean connected, Personnel user) {
		super();
		this.connected = connected;
		this.user = user;
	}
	
	public MenuCommun() {}

	protected void deconnexion() {
		connected = false;		
	}
	
	protected void consulterStocks() {
		ProduitDAO produitDAO = new ProduitDAOImpl();

		ArrayList<Produit> produits = new ArrayList<Produit>();
		produits = produitDAO.listProduit();

		showStockProduits(produits);

	}
	
	protected void consulterProduitsDispos() {
		ProduitDAO produitDAO = new ProduitDAOImpl();

		ArrayList<Produit> produits = new ArrayList<Produit>();
		produits = produitDAO.getProduitsDispos();

		showStockProduits(produits);
	}
	
	protected void showStockProduits(ArrayList<Produit> list) {

		System.out.println("Liste des stocks :\n");

		list.forEach(prod -> System.out.println(prod.getId() + ". " + prod.getLibelle() + " : " + prod.getQuantite()));

		System.out.println();
	}
	
	protected void listerPlatsSelonCateg() {
		CategoriePlatDAO categoriePlatDAO = new CategoriePlatDAOImpl();
		PlatDAO platDAO = new PlatDAOImpl();
		
		ArrayList<CategoriePlat> listcategPlat = categoriePlatDAO.getAll();
		
		listcategPlat.forEach(plat -> System.out.println(plat.getId() + ". " + plat.getLibelle()));
		
		System.out.println();
		System.out.println("Sélectionnez la catégorie : \n");
		
		int intIdCateg = ScanEntree.readIntegerWithDelimitations(0, listcategPlat.size()-1);
		CategoriePlat categ = listcategPlat.get(intIdCateg);
		
		ArrayList<Plat> listPlatsCateg = platDAO.listerPlatSelonCategorie(categ.getId());
		
		if(listPlatsCateg.size()==0)
			System.out.println("Il n'y a aucun plat dans cette catégorie");
		else {
			for(Plat plat : listPlatsCateg) {
				System.out.println(plat.getLibelle());
			}
		}		
	}
	
}
