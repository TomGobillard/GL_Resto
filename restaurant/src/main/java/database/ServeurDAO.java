package database;

import Models.Serveur;

public abstract class ServeurDAO extends DAO<Serveur> {

	public abstract void printOccupationTablesWithAvancement();
	public abstract void printOccupationAllTables();

}
