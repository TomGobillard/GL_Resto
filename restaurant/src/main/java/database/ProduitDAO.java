package database;

import Models.Produit;

public abstract class ProduitDAO<T> extends DAO<Produit>{

	public abstract T listProduit ();

}
