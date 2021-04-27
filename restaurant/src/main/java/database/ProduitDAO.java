package database;

import java.util.ArrayList;
import java.util.HashMap;

import Models.Produit;

public abstract class ProduitDAO<T> extends DAO<Produit>{

	public abstract ArrayList<T> listProduit ();
	public abstract HashMap<Long, T> listProduits();
	public abstract boolean isDispo(long idProduit, int qte);
}
