package fr.ul.miage.restaurant.databse;

import java.util.ArrayList;

import fr.ul.miage.restaurant.Models.Plat;
import fr.ul.miage.restaurant.Models.Produit;

public abstract class PlatDAO extends DAO<Plat>{

	public abstract void creerPlat(String nom, int prix, ArrayList<Produit> ingredients, int idCateg);

	public abstract void listerPlatSelonCategorie();

}
