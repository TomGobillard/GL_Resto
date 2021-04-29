package fr.ul.miage.restaurant.dao;

public abstract class PersonnelDAO<T> extends DAO<T>{
	public abstract T connection (String login, String mdp);
}
