package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.dao.ProduitDAO;

public class ProduitDAOImplTest {
	
	private ProduitDAO produitDAO;
	
	@Before
	public void setUp() throws Exception {
		produitDAO = new ProduitDAOImpl();
	}
	
	@Test
	public void testListProduits() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();
		assertEquals("Champignons", listProduits.get(0).getLibelle());
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

}
