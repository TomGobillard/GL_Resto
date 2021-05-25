package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.menus.MenuDirecteur;
import fr.ul.miage.restaurant.models.Plat;

public class DirecteurTest {
	
	MenuDirecteur menuDirecteur;
	PlatDAO platDAO;
	
	@Before
	public void setUp() throws Exception {
		menuDirecteur = new MenuDirecteur(true, null);
		platDAO = new PlatDAOImpl();
	}

	@Test
	public void testAjoutPlatCarteduJour() {
		long idPlat = platDAO.getAll().get(0).getId();
		
		menuDirecteur.ajoutPlatCarteduJour_Action(idPlat);
		
		Plat plat = platDAO.find(idPlat);
		
		assertEquals(true, plat.isPlatDuJour());
	}

}
