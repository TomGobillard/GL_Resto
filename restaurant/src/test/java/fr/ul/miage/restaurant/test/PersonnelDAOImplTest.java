package fr.ul.miage.restaurant.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.dao.PersonnelDAO;
import fr.ul.miage.restaurant.models.Personnel;

public class PersonnelDAOImplTest {

	private PersonnelDAO<Personnel> personnelDAO;
	
	@Before
	public void setUp() throws Exception {
		personnelDAO = new PersonnelDAOImpl();
	}
	
	/*
	 * On vérifie qu'on récupère bien le nom associé au personnel qui à pour identifiant 2
	 */
	@Test
	public void testFind() {
		Personnel personnel = personnelDAO.find(2);
		assertEquals("tom", personnel.getNom());
	}
	
	/*
	 * On vérifie qu'on récupère bien le nom du premier personnel
	 */
	@Test
	public void testGetAll() {
		ArrayList<Personnel> personnels = personnelDAO.getAll();
		
		assertEquals("tom", personnels.get(0).getNom());
	}
	
	/*
	 * On vérifie que la liste du personnel a une taille supérieure ou égale à 0
	 */
	@Test
	public void testGetAllNotNull() {
		ArrayList<Personnel> personnels = personnelDAO.getAll();
		
		assertTrue(personnels.size()>=0);
	}
	
	/*
	 * Pour la liste de tests suivante, on vérifie que les méthodes permettent de récupérer les bons rôles
	 */
	@Test
	public void testConnectDirecteur() {
		Personnel personnel = personnelDAO.getByRole("directeur");
		assertEquals("DIRECTEUR", personnel.getRole());
	}
	
	@Test
	public void testConnectAssistantService() {
		Personnel personnel = personnelDAO.getByRole("assistant service");
		assertEquals("ASSISTANT SERVICE", personnel.getRole());
	}
	
	@Test
	public void testConnectCuisinier() {
		Personnel personnel = personnelDAO.getByRole("cuisinier");
		assertEquals("CUISINIER", personnel.getRole());
	}
	
	@Test
	public void testConnectServeur() {
		Personnel personnel = personnelDAO.getByRole("serveur");
		assertEquals("SERVEUR", personnel.getRole());
	}
	
	@Test
	public void testConnectMaitreHotel() {
		Personnel personnel = personnelDAO.getByRole("maitre hotel");
		assertEquals("MAITRE HOTEL", personnel.getRole());
	}
	
	/*
	 * On vérifie que la modification du login d'un personnel est bien prise en compte dans la base.
	 */
	@Test
	public void testUpdateLoginPersonnel() {
		ArrayList<Personnel> listPersonnel = personnelDAO.getAll();
		if(listPersonnel.size()>0) {
			Personnel personnel = listPersonnel.get(0);
			Long idPersonnel = personnel.getId();
			
			String oldLogin = personnel.getLogin();
			String newLogin = "newLogin";
			personnel.setLogin(newLogin);
			
			personnelDAO.update(personnel);
			
			personnel = personnelDAO.find(idPersonnel);
			
			assertEquals("newLogin", personnel.getLogin());
			
			personnel.setLogin(oldLogin);
			
			personnelDAO.update(personnel);
		}
	}

}
