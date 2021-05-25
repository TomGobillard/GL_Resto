package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.menus.MenuAssistantService;
import fr.ul.miage.restaurant.models.Table;

public class AssistantServiceTest {
	
	private MenuAssistantService menuAssistant;
	private TableDAO tableDAO;

	@Before
	public void setUp() throws Exception {
		menuAssistant = new MenuAssistantService(true, null);
		tableDAO = new TableDAOImpl();
	}
	
	@Test
	public void testDresserTable() {
		long idTable = tableDAO.getAll().get(0).getId();
		
		menuAssistant.dresserTableAction(idTable);
		
		Table table = tableDAO.find(idTable);
		
		assertEquals("PROPRE", table.getEtat());
		
		assertEquals("VIDE", table.getAvancement());
	}

}
