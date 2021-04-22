package restaurant;

import Models.Personnel;
import database.DAO;
import database.PersonnelDAO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAO<Personnel> personnelDAO = new PersonnelDAO();

		System.out.println(personnelDAO.find(0));
	}

}
