package fr.ul.miage.restaurant.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.ServeurDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.ServeurDAO;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.menus.MenuServeur;
import fr.ul.miage.restaurant.models.Serveur;
import fr.ul.miage.restaurant.models.Table;

public class TableDAOImplTest {

	private TableDAO tableDAO;
	private ServeurDAO serveurDAO;

	@Before
	public void setUp() throws Exception {
		tableDAO = new TableDAOImpl();
		serveurDAO = new ServeurDAOImpl();
	}

	@Test
	public void testAssignTableServeur() {
		Table table = new Table();
		
		int idTable = 1;
		
		tableDAO.assignServeur(2, idTable);
		table = tableDAO.find(idTable);
		
		assertEquals(2, (int) table.getIdServeur());
	}
	
	@Test
	public void testGetTablesDispoServeur() {
		ArrayList<Integer> listIdTables = new ArrayList<>();
		ArrayList<Table> listTables = new ArrayList<Table>();
		ArrayList<Serveur> listServeur = serveurDAO.getAll();
		Serveur serveur;
		Table table;
		
		if(listServeur.size()>0) {
			serveur = listServeur.get(0);
			
			listIdTables = tableDAO.getServeurTablesLibres(serveur.getId());
			
			for(int idTable : listIdTables) {
				table = tableDAO.find(idTable);
				
				assertEquals("PROPRE", table.getEtat().toUpperCase());
			}	
		}
	}
	
	@Test
	public void testInstallClient() {
		MenuServeur menuServeur = new MenuServeur();
		ArrayList<Integer> listIdTables = new ArrayList<>();
		ArrayList<Table> listTables = new ArrayList<Table>();
		ArrayList<Serveur> listServeur = serveurDAO.getAll();
		Serveur serveur;
		Table table;
		
		if(listServeur.size()>0) {
			serveur = listServeur.get(0);
			
			listIdTables = tableDAO.getServeurTablesLibres(serveur.getId());
			
			if(listIdTables.size()>0) {
				table = tableDAO.find(listIdTables.get(0));
				menuServeur.installerClientAction((int) table.getId());
				
				table = tableDAO.find(listIdTables.get(0));
				
				assertEquals("OCCUPEE", table.getEtat().toUpperCase());
				
				tableDAO.initTableTest(table.getId());
			}
		}
	}
	
	@Test
	public void testDresserTable_Propre() {
		long idTable = tableDAO.getAll().get(0).getId();

		tableDAO.dresserTable(idTable);

		Table table = tableDAO.find(idTable);

		assertEquals("PROPRE", table.getEtat());
	}

	@Test
	public void testDresserTable_Vide() {
		long idTable = tableDAO.getAll().get(0).getId();

		tableDAO.dresserTable(idTable);

		Table table = tableDAO.find(idTable);

		assertEquals("VIDE", table.getAvancement());
	}
	
	@Test
	public void testEtatValues() {
		ArrayList<Table> tables = tableDAO.getAll();
		for (Table table : tables) {
			assertTrue(table.getEtat().equals("PROPRE") || table.getEtat().equals("SALE") || table.getEtat().equals("OCCUPEE") || table.getEtat().equals("RESERVEE"));
		}
	}

	@Test
	public void testRepasFini() {
		ArrayList<Table> tables = tableDAO.getTableRepasFini();
		for (Table table : tables) {
			assertEquals("EN REPAS", table.getAvancement());
		}
	}
	
	@Test
	public void testRepasADresserOuServir() {
		ArrayList<Table> tables = tableDAO.getTablesADresserOuRanger();
		for (Table table : tables) {
			assertEquals("SALE", table.getEtat());
		}
	}
}
