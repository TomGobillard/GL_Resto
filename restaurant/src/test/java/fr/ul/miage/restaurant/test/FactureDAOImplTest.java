package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.FactureDAOImpl;
import fr.ul.miage.restaurant.dao.FactureDAO;

public class FactureDAOImplTest {
	
	FactureDAO factureDAO;
	
	@Before
	public void setUp() throws Exception {
		factureDAO = new FactureDAOImpl();
	}

	@Test
	public void testProfitDejeuner() {
		int profit = factureDAO.profitDejeuner();
			
		assertTrue(profit>=0);
	}
	
	@Test
	public void testProfitDiner() {
		int profit = factureDAO.profitDiner();
		
		assertTrue(profit>=0);
	}
	
	@Test
	public void testRecetteQuotidienne() {
		int recette = factureDAO.getRecetteQuotidienne();
		
		assertTrue(recette>=0);
	}
	
	@Test
	public void testRecetteHebdo() {
		int recette = factureDAO.getRecetteHebdo();
		
		assertTrue(recette>=0);
	}
	
	@Test
	public void testRecetteMensuelle() {
		int recette = factureDAO.getRecetteMensuelle();
		
		assertTrue(recette>=0);
	}
	

}
