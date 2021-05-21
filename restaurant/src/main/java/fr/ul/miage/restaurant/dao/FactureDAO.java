package fr.ul.miage.restaurant.dao;

import fr.ul.miage.restaurant.models.Facture;

public abstract class FactureDAO extends DAO<Facture>{

		public abstract Facture genererFacture(long idclient, String repas);
		public abstract int profitDejeuner();
		public abstract int profitDiner();
		public abstract int getRecetteQuotidienne();
		public abstract int getRecetteHebdo();
		public abstract int getRecetteMensuelle();
	

}
