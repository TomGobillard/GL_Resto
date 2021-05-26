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

public class CommandeTest {

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

//	@Test
//	public void Test_MajStockAfterCommande() {
//		Table table = tableDAO.getAll().get(0);
//
//		//commandeDao.creerCommande((int) table.getId());
//
//		Plat plat = platDAO.getAll().get(0);
//
//		ArrayList<Plat> plats = new ArrayList<Plat>();
//		plats.add(plat);
//
//		ArrayList<CompositionPlat> listCompoPlat = compositionPlatDAO.getWithPlats(plats);
//
//		Long idProduit = listCompoPlat.get(0).getIdProduit();
//
//		Produit produit = (Produit) produitDAO.find(idProduit);
//		int qteInit = produit.getQuantite();
//
//		menuServeur.validerCommande(plats, (int) table.getId());
//
//		produit = (Produit) produitDAO.find(idProduit);
//		int qteModif = produit.getQuantite();
//
//		commandeDao.deleteLastCmdeforTest();
//
//		int qteExpected = qteInit - listCompoPlat.get(0).getQuantite();
//
//		//assertEquals(qteExpected, qteModif);
//	}

//	@Test
//	public void Test_MajNbCommandePlat() {
//		Table table = tableDAO.getAll().get(0);
//
//		Plat plat = platDAO.getAll().get(0);
//
//		ArrayList<Plat> plats = new ArrayList<Plat>();
//		plats.add(plat);
//
//		ArrayList<CompositionPlat> listCompoPlat = compositionPlatDAO.getWithPlats(plats);
//
//		int popInitPlat = (int) plat.getNbCommandes();
//
//		menuServeur.validerCommande(plats, (int) table.getId());
//
//		plat = platDAO.getAll().get(0);
//		
//		int popModifPlat = (int) plat.getNbCommandes();
//
//		commandeDao.deleteLastCmdeforTest();
//
//		assertEquals(popInitPlat+1, popModifPlat);
//	}

}
