package fr.ul.miage.restaurant.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.models.Plat;

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
	
	@Test
	public void testAjoutPlatCarteduJour() {
		long idPlat = platDAO.getAll().get(0).getId();

		platDAO.ajoutPlatCarteduJour(idPlat);

		Plat plat = platDAO.find(idPlat);

		platDAO.initCarteduJour();

		assertEquals(true, plat.isPlatDuJour());
	}

}
