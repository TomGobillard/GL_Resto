package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.models.Table;

public class TableTest {

	private TableDAO tableDAO;

	@Before
	public void setUp() throws Exception {
		tableDAO = new TableDAOImpl();
	}

	@Test
	public void testAssignTableServeur() {
		Table table = new Table();
		
		int idTable = 1;
		
		tableDAO.assignServeur(2, idTable);
		table = tableDAO.find(idTable);
		
		assertEquals(2, (int) table.getIdServeur());
	}

}
