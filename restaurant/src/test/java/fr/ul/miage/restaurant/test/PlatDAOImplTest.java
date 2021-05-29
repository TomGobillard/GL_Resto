package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;

public class PlatDAOImplTest {

private PlatDAO platDAO;
	
	@Before
	public void setUp() throws Exception {
		platDAO = new PlatDAOImpl();
	}
	
	@Test
	public void testListProduits() {
		ArrayList<Plat> listProduits = platDAO.getAll();
		assertTrue(listProduits.size() >= 0);
	}

}
