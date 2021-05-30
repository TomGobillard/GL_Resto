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
	
	/*
	 * Une fois un plat renseigné on ne peut pas le supprimer de la base, donc on vérifie la conformité d'un plat parmis les autres.
	 */
	
	@Test
	public void testFind() {
		Plat plat = platDAO.find(27);

		assertEquals("Salade de carottes", plat.getLibelle());
	}
	
	/*
	 * On vérifie pour chaque plat que le champ nbCommande possède une valeur positive.
	 */
	
	@Test
	public void nbCommandePositif() {
		ArrayList<Plat> plats = platDAO.getAll();
		for (Plat plat : plats) {
			assertTrue(plat.getNbCommandes() >= 0);
		}
	}
	
}
