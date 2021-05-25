package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.restaurant.models.Table;

public abstract class TableDAO extends DAO<Table>{

	public abstract Table obtenirInfoTable(long idTable);
	public abstract void printOccupationAllTables();
	public abstract boolean tableExists(long idTable);
	public abstract void assignServeur(long idServeur, long idTable);
	public abstract void showAvancement(long idTable);
	public abstract HashMap<Integer, String> getTableForInitPrint();
	public abstract ArrayList<Integer> getServeurTablesId(long serveurId);
	public abstract ArrayList<Integer> getServeurTablesLibres(long serveurId);
	public abstract void installerClient(long idClient, long idTable);
	public abstract void initTableTest(long idTable);
	public abstract ArrayList<Table> getTableRepasFini();
	public abstract ArrayList<Table> getTablesADresserOuRanger();
	public abstract void dresserTable(long idTable);
	

}
