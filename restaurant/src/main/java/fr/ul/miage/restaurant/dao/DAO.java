package fr.ul.miage.restaurant.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.ul.miage.restaurant.postgre.ConnectionPostgresSQL;

public abstract class DAO<T> {

	public Connection connect = ConnectionPostgresSQL.getInstance();
	
	public abstract T find(long id);
	
	public abstract T create (T obj);
	
	public abstract T update (T obj);
	
	public abstract void delete (T obj);
	
	public abstract ArrayList<T> getAll ();
	
}