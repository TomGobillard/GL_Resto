package fr.ul.miage.restaurant.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.dao.PersonnelDAO;

public class PersonnelDAOImplTest {

	private PersonnelDAO<Personnel> personnelDAO;
	
	@Before
	public void setUp() throws Exception {
		personnelDAO = new PersonnelDAOImpl();
	}
	
	@Test
	public void testConnectDirecteur() {
		Personnel personnel = personnelDAO.connection("dirlo", "mdp");
		assertEquals("DIRECTEUR", personnel.getRole());
	}

}
