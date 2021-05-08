package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;
import java.util.HashMap;

import fr.ul.miage.restaurant.models.Table;

public abstract class TableDAO extends DAO<Table>{

	public abstract void obtenirInfoTable();
	public abstract void printOccupationAllTables();
	public abstract boolean tableExists(long idTable);
	public abstract void assignServeur(long idServeur, long idTable);
	public abstract void showAvancement(long idTable);
	public abstract HashMap<Integer, String> getTableForInitPrint();
	public abstract ArrayList<Integer> getServeurTables(long serveurId);
	

}
