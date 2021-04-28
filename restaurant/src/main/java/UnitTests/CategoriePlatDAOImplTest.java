package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Models.CategoriePlat;
import fr.ul.miage.restaurant.databse.CategoriePlatDAO;
import fr.ul.miage.restaurant.databse.CategoriePlatDAOImpl;

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
