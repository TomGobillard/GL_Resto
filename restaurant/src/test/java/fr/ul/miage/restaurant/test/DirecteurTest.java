package fr.ul.miage.restaurant.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fr.ul.miage.restaurant.Impl.PersonnelDAOImpl;
import fr.ul.miage.restaurant.Impl.PlatDAOImpl;
import fr.ul.miage.restaurant.Impl.ProduitDAOImpl;
import fr.ul.miage.restaurant.dao.PersonnelDAO;
import fr.ul.miage.restaurant.dao.PlatDAO;
import fr.ul.miage.restaurant.dao.ProduitDAO;
import fr.ul.miage.restaurant.menus.MenuDirecteur;
import fr.ul.miage.restaurant.models.Personnel;
import fr.ul.miage.restaurant.models.Plat;
import fr.ul.miage.restaurant.models.Produit;

public class DirecteurTest {

	MenuDirecteur menuDirecteur;
	PlatDAO platDAO;
	ProduitDAO<Produit> produitDAO;
	PersonnelDAO<Personnel> personnelDAO;

	@Before
	public void setUp() throws Exception {
		menuDirecteur = new MenuDirecteur(true, null);
		platDAO = new PlatDAOImpl();
		produitDAO = new ProduitDAOImpl();
		personnelDAO = new PersonnelDAOImpl();
	}

	@Test
	public void testAjoutPlatCarteduJour() {
		long idPlat = platDAO.getAll().get(0).getId();

		menuDirecteur.ajoutPlatCarteduJour_Action(idPlat);

		Plat plat = platDAO.find(idPlat);

		platDAO.initCarteduJour();

		assertEquals(true, plat.isPlatDuJour());
	}

	@Test
	public void testMajStockProduit() {
		ArrayList<Produit> listProduits = produitDAO.listProduit();
		if(listProduits.size()>0) {
			Produit produit = listProduits.get(0);
			long idProduit = produit.getId();

			int stockInit = listProduits.get(0).getQuantite();
			int nouveauStock = stockInit + 10;

			produit.setQuantite(nouveauStock);
			produitDAO.update(produit);

			Produit majProduit = produitDAO.find(idProduit);

			assertEquals(nouveauStock, majProduit.getQuantite());

			produit.setQuantite(stockInit);
			produitDAO.update(produit);
		}
	}
	
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
