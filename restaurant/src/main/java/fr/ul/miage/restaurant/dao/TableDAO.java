package fr.ul.miage.restaurant.dao;

import fr.ul.miage.restaurant.models.Table;

public abstract class TableDAO extends DAO<Table>{

	public abstract void obtenirInfoTable();
	public abstract void printOccupationTablesWithAvancement();
	public abstract void printOccupationAllTables();
	public abstract boolean tableExists(long idTable);
	public abstract void assignServeur(long idServeur, long idTable);
	

}
