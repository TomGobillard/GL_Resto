package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Produit;

public abstract class ProduitDAO extends DAO<Produit>{

	public abstract ArrayList<Produit> listProduit ();
	public abstract HashMap<Long, Produit> listProduits();
	public abstract boolean isDispo(long idProduit, int qte);
	public abstract void updateQuantite(CompositionPlat compoPlat);
	public abstract ArrayList<Produit> getProduitsDispos();

}
