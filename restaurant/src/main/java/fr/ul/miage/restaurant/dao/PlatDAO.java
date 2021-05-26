package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;

import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;

public abstract class PlatDAO extends DAO<Plat>{

	public abstract void creerPlat(String nom, double prix, ArrayList<Produit> ingredients, int idCateg);

	public abstract ArrayList<Plat> listerPlatSelonCategorie(long idCateg);
	
	public abstract ArrayList<Plat> getCarteduJour();
	
	public abstract void initCarteduJour();
		
	public abstract ArrayList<Plat> platsDispoCateg(long idCateg);
	
	public abstract void ajoutPlatCarteduJour(long idPlat);
	
	public abstract void incrementeNbCommandes(ArrayList<Plat> plats);
	
	public abstract void setEtatPlatPret();
	
	public abstract void setEtatPlatServis(long id);
	
	public abstract ArrayList<Plat> platsPopulaires();
	
	public abstract boolean isPlatEnPreparation(long idPlat, long idCmde);
}
