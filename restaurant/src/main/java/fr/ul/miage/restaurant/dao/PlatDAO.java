package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;

import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;

public abstract class PlatDAO extends DAO<Plat>{

	public abstract void creerPlat(String nom, int prix, ArrayList<Produit> ingredients, int idCateg);

	public abstract void listerPlatSelonCategorie();
	
	public abstract ArrayList<Plat> getCarteduJour();
	
	public abstract void initCarteduJour();
	
	public abstract ArrayList<Plat> platsCateg();
	
	public abstract ArrayList<Plat> platsDispoCateg();
	
	public abstract void ajoutPlatCarteduJour(long idPlat);
	
	public abstract void incrementeNbCommandes(ArrayList<Plat> plats);
	
	public abstract void setEtatPlat();
	
	public abstract ArrayList<Plat> platsPopulaires();
}
