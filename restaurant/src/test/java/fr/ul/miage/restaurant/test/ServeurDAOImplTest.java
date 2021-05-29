package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.Impl.ServeurDAOImpl;
import fr.ul.miage.restaurant.dao.ServeurDAO;
import fr.ul.miage.restaurant.menus.MenuDirecteur;
import fr.ul.miage.restaurant.models.Serveur;

public class ServeurDAOImplTest {
	
	ServeurDAO serveurDAO;
	
	@Before
	public void setUp() throws Exception {
		serveurDAO = new ServeurDAOImpl();
	}

	@Test
	public void testServeurExists() {
		boolean exists = serveurDAO.serveurExists(2);
		
		assertTrue(exists);
	}
	
	@Test
	public void testGetAllServeurs() {
		ArrayList<Serveur> serveurs = serveurDAO.getAll();
		
		assertEquals("SERVEUR", serveurs.get(0).getRole().toUpperCase());
	}
}
