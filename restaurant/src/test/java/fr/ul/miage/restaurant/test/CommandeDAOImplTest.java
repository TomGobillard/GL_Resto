package fr.ul.miage.restaurant.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.CommandeDAOImpl;
import fr.ul.miage.restaurant.Impl.CompositionPlatDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.Impl.TableDAOImpl;
import fr.ul.miage.restaurant.dao.CommandeDAO;
import fr.ul.miage.restaurant.dao.CompositionPlatDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.dao.TableDAO;
import fr.ul.miage.restaurant.menus.MenuServeur;
import fr.ul.miage.restaurant.models.CompositionPlat;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;
import fr.ul.miage.restaurant.models.Table;

public class CommandeDAOImplTest {

	private CommandeDAO commandeDao;
	private TableDAO tableDAO;
	private PlatDAO platDAO;
	private ProduitDAO produitDAO;
	private CompositionPlatDAO compositionPlatDAO;
	private MenuServeur menuServeur;

	@Before
	public void setUp() throws Exception {
		commandeDao = new CommandeDAOImpl();
		tableDAO = new TableDAOImpl();
		platDAO = new PlatDAOImpl();
		produitDAO = new ProduitDAOImpl();
		compositionPlatDAO = new CompositionPlatDAOImpl();
		menuServeur = new MenuServeur();
	}
}
