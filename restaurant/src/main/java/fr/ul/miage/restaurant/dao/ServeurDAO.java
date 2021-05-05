package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;

import fr.ul.miage.restaurant.models.Serveur;

public abstract class ServeurDAO extends DAO<Serveur> {

	public abstract void printOccupationTablesWithAvancement();
	public abstract void printOccupationAllTables();
	public abstract ArrayList<Integer> get();

}
