package fr.ul.miage.restaurant.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;
import fr.ul.miage.restaurant.models.CategoriePlat;

public class CategoriePlatDAOImplTest {
	
	private CategoriePlatDAO categPlatDAO;
	
	@Before
	public void setUp() throws Exception {
		categPlatDAO = new CategoriePlatDAOImpl();
	}

	@Test
	public void TestGetCategEntree() {
		CategoriePlat categPlat = categPlatDAO.find(1);
		assertEquals("Entree", categPlat.getLibelle());
	}
	
	@Test
	public void TestGetCategPlatViande() {
		CategoriePlat categPlat = categPlatDAO.find(2);
		assertEquals("Plat-Viande", categPlat.getLibelle());
	}
	
	@Test
	public void TestGetCategPoisson() {
		CategoriePlat categPlat = categPlatDAO.find(3);
		assertEquals("Plat-Poisson", categPlat.getLibelle());
	}
	
	@Test
	public void TestGetCategPlatVege() {
		CategoriePlat categPlat = categPlatDAO.find(4);
		assertEquals("Plat-Végétarien", categPlat.getLibelle());
	}
	
	@Test
	public void TestGetCategDessert() {
		CategoriePlat categPlat = categPlatDAO.find(5);
		assertEquals("Dessert", categPlat.getLibelle());
	}
	
	@Test
	public void TestGetCategEnfant() {
		CategoriePlat categPlat = categPlatDAO.find(6);
		assertEquals("Enfant", categPlat.getLibelle());
	}
	
	@Test
	public void TestGetAllCateg() {
		ArrayList<CategoriePlat> listCateg = categPlatDAO.getAllCateg();
		assertEquals("Entree", listCateg.get(0).getLibelle());
	}

}
