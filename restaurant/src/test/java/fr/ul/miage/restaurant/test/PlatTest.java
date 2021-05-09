package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.Produit;

public class PlatTest {

private ProduitDAO<Produit> produitDAO;
	
	@Before
	public void setUp() throws Exception {
		produitDAO = new ProduitDAOImpl();
	}
	
	@Test
	public void testListProduits() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();
		assertEquals("Champignons", listProduits.get(0).getLibelle());
	}

}
