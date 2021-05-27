package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;

import fr.ul.miage.restaurant.models.CompositionCmde;

public abstract class CompositionCmdeDAO extends DAO<CompositionCmde>{
	
	public abstract ArrayList<CompositionCmde> getCompoCmdesWithServeur(long id);
	public abstract void setEtatPlatsServis(long idCommande,long idPlat);

}
