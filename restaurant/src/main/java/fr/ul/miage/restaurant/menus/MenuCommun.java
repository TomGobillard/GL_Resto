package fr.ul.miage.restaurant.menus;

import java.util.ArrayList;

import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Produit;

public class MenuCommun {
	boolean connected=false;
	Personnel user;

	public MenuCommun(boolean connected, Personnel user) {
		super();
		this.connected = connected;
		this.user = user;
	}

	protected void deconnexion() {
		connected=false;		
	}
	
	protected void consulterStocks() {
		ProduitDAO<Produit> produitDAO = new ProduitDAOImpl();

		ArrayList<Produit> produits = new ArrayList<Produit>();
		produits = produitDAO.listProduit();

		System.out.println("Liste des stocks :\n");

		for(int i = 0; i < produits.size(); i++) {
			System.out.println(produits.get(i).getId() + "; " + produits.get(i).getLibelle() + " : " + produits.get(i).getQuantite());
		}

		System.out.println();
	}

}
