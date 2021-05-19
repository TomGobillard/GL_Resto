package fr.ul.miage.restaurant.dao;

import java.sql.Timestamp;

import fr.ul.miage.restaurant.models.Commande;

public abstract class CommandeDAO extends DAO<Commande>{

	public abstract void creerCommande(int idTable);
	public abstract void creerCompositionCmde(int idTable, int idPlat);
	public abstract int getLastCommande();
	public abstract void deleteLastCmdeforTest();
	public abstract String getCommandeEntrantes();

	public abstract boolean cmdeEntranteExists(long idCmde);
	public abstract void showPlatCommande(long idCmde);
	
	public abstract Timestamp getTempsCommandesFinies();
}
