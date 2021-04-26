package database;

public abstract class PersonnelDAO<T> extends DAO<T>{
	public abstract T connection (String login, String mdp);
}
