package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.CategoriePlatDAOImpl;
import fr.ul.miage.restaurant.models.CategoriePlat;
import fr.ul.miage.restaurant.dao.CategoriePlatDAO;

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
	public void TestGetAllCateg() {
		ArrayList<CategoriePlat> listCateg = categPlatDAO.getAllCateg();
		assertEquals("Entree", listCateg.get(0).getLibelle());
	}

}
