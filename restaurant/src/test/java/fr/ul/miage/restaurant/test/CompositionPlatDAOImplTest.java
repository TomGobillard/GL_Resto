package fr.ul.miage.restaurant.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.CompositionPlatDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.dao.CompositionPlatDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Plat;

public class CompositionPlatDAOImplTest {

	private CompositionPlatDAO compoPlatDAO;
	
	@Before
	public void setUp() throws Exception {
		compoPlatDAO = new CompositionPlatDAOImpl();
	}
	
	/*
	 * On vérifie qu'on récupère bien le bon identifiant du premier ingrédient pour la composition du plat n°27
	 */
	@Test
	public void testCompoPlatId() {
		PlatDAO platDAO = new PlatDAOImpl();
		Plat plat = platDAO.find(27);
		ArrayList<Plat> plats = new ArrayList<Plat>();
		plats.add(plat);
		
		ArrayList<CompositionPlat> compoPlats = compoPlatDAO.getWithPlats(plats);
		
		assertEquals(7, compoPlats.get(0).getIdProduit());
		
	}
	
	/*
	 * On vérifie qu'on récupère bien la bonne quantité du premier ingrédient pour la composotion du plat n°27
	 */
	@Test
	public void testCompoPlatQuantite() {
		PlatDAO platDAO = new PlatDAOImpl();
		Plat plat = platDAO.find(27);
		ArrayList<Plat> plats = new ArrayList<Plat>();
		plats.add(plat);
		
		ArrayList<CompositionPlat> compoPlats = compoPlatDAO.getWithPlats(plats);
		
		assertEquals(5, compoPlats.get(0).getQuantite());
		
	}

}
