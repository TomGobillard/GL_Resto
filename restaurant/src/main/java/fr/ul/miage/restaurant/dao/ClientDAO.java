package fr.ul.miage.restaurant.dao;

import java.sql.Timestamp;

import fr.ul.miage.restaurant.models.Client;

public abstract class ClientDAO extends DAO<Client>{

	public abstract Timestamp getRotationTimeAvg();



}
