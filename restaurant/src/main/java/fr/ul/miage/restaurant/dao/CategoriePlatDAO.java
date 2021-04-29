package fr.ul.miage.restaurant.dao;

import java.util.ArrayList;

import fr.ul.miage.restaurant.Models.CategoriePlat;

public abstract class CategoriePlatDAO extends DAO<CategoriePlat> {
	public abstract ArrayList<CategoriePlat> getAllCateg();
}
