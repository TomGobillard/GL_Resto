package fr.ul.miage.restaurant.databse;

import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.restaurant.Models.Produit;

public abstract class ProduitDAO<T> extends DAO<Produit>{

	public abstract ArrayList<T> listProduit ();
	public abstract HashMap<Long, T> listProduits();
	public abstract boolean isDispo(long idProduit, int qte);
}
