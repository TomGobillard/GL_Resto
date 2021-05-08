package fr.ul.miage.restaurant.dao;

import fr.ul.miage.restaurant.models.Serveur;

public abstract class ServeurDAO extends DAO<Serveur> {

	public abstract boolean serveurExists(long idServeur);
}
