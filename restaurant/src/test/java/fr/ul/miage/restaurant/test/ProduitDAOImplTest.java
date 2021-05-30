package fr.ul.miage.restaurant.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.Produit;

public class ProduitDAOImplTest {
	
	public ProduitDAO<Produit> produitDAO;
	
	@Before
	public void setUp() throws Exception {
		produitDAO = new ProduitDAOImpl();
	}

	@Test
	public void testListProduits() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();
		assertTrue(0 <= listProduits.size());
	}
	
	@Test
	public void testGetProduits() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();
		assertEquals("Champignons", listProduits.get(0).getLibelle());
	}
	
	@Test
	public void testStockProduit() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();

		for (Produit produit : listProduits) {
			assertTrue(produit.getQuantite() >=0 );
		}
	}
	
	@Test
	public void testMajStockProduit() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();
		if(!listProduits.isEmpty()) {
			Produit produit = listProduits.get(0);
			long idProduit = produit.getId();

			int stockInit = listProduits.get(0).getQuantite();
			int nouveauStock = stockInit + 10;

			produit.setQuantite(nouveauStock);
			produitDAO.update(produit);

			Produit majProduit = produitDAO.find(idProduit);

			assertEquals(nouveauStock, majProduit.getQuantite());

			produit.setQuantite(stockInit);
			produitDAO.update(produit);
		}
	}
	
	@Test
	public void testFind() {
		Produit prod = produitDAO.find(3);
		
		assertEquals("Lait", prod.getLibelle());
	}
}
