package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;

import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;

public abstract class PlatDAO extends DAO<Plat>{

	public abstract void creerPlat(String nom, int prix, ArrayList<Produit> ingredients, int idCateg);

	public abstract void listerPlatSelonCategorie();

}
