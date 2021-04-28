package database;

import java.util.ArrayList;

import Models.Plat;
import Models.Produit;

public abstract class PlatDAO extends DAO<Plat>{

	public abstract void creerPlat(String nom, int prix, ArrayList<Produit> ingredients);

	public abstract void listerPlatSelonCategorie();

}
