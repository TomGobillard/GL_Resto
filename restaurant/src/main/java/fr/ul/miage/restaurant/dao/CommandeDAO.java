package fr.ul.miage.restaurant.dao;

import fr.ul.miage.restaurant.models.Commande;

public abstract class CommandeDAO extends DAO<Commande>{

	public abstract void creerCommande(int idTable);
	public abstract void creerCompositionCmde(int idTable, int idPlat);
	public abstract int getLastCommande();
<<<<<<< HEAD
	public abstract void deleteLastCmdeforTest();
	public abstract String getCommandeEntrantes();

=======
	public abstract boolean cmdeEntranteExists(long idCmde);
	public abstract void showPlatCommande(long idCmde);
>>>>>>> 2bc419be778980034d570f23a4090276552eb223
}
