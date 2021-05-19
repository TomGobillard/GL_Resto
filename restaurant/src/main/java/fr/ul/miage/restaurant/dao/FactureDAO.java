package fr.ul.miage.restaurant.dao;

import fr.ul.miage.restaurant.models.Facture;

public abstract class FactureDAO extends DAO<Facture>{

		public abstract Facture genererFacture(long idclient, String repas);
	

}
