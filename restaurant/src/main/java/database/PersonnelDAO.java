package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Personnel;

public class PersonnelDAO extends DAO<Personnel> {

	@Override
	public Personnel find(long id) {
		// TODO Auto-generated method stub
		Personnel personnel = new Personnel();
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM personnel WHERE identifiant = \'0\'"
							);
			
			if(result.first())
				System.out.println(result.getString("role"));
				personnel = new Personnel(id, result.getString("role"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return personnel;
	}

	@Override
	public Personnel create(Personnel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personnel update(Personnel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Personnel obj) {
		// TODO Auto-generated method stub
		
	}

}

