package restaurant;

import Models.Personnel;
import database.DAO;
import database.PersonnelDAO;
import database.PersonnelDAOImpl;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonnelDAO<Personnel> personnelDAO = new PersonnelDAOImpl();
		
		System.out.println(personnelDAO.connection("", ""));

	}

}
