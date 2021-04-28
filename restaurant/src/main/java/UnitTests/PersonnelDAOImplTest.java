package UnitTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Models.Personnel;
import fr.ul.miage.restaurant.databse.PersonnelDAO;
import fr.ul.miage.restaurant.databse.PersonnelDAOImpl;

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
