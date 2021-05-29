package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.dao.ProduitDAO;

public class ProduitDAOImplTest {
	
	private ProduitDAO<Produit> produitDAO;
	
	@Mock private ProduitDAO<Produit> mock_produitDAO;
	@Rule public MockitoRule rule = MockitoJUnit.rule();
	
	@Before
	public void setUp() throws Exception {
		produitDAO = new ProduitDAOImpl();
	}
	
	@Test
	public void testGetProduitbyName() {
		Mockito.when(produitDAO.create(null)).thenReturn(null);
		//Produit actual = 
	}
	
	@Test
	public void testListProduits() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();
		assertNotEquals(0, listProduits.size());
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

	private Produit createTestProduit() {
		Produit produit = new Produit();
		produit.setLibelle("Salade");
		return produit;
	}
}
