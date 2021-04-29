package fr.ul.miage.restaurant.dao;

import fr.ul.miage.restaurant.Models.Serveur;

public abstract class ServeurDAO extends DAO<Serveur> {

	public abstract void printOccupationTablesWithAvancement();
	public abstract void printOccupationAllTables();

}
