package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import Models.CategoriePlat;

public  class CategoriePlatDAO extends DAO<CategoriePlat> {

	@Override
	public CategoriePlat find(long id) {
		// TODO Auto-generated method stub
		CategoriePlat categoriePlat = new CategoriePlat();
		
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(
							"SELECT * FROM categoriePlat WHERE idCategorie = \'0\'"
							);
			
			if(result.first())
				System.out.println(result.getString("libelle"));
			categoriePlat = new CategoriePlat(id, result.getString("libelle"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categoriePlat;
	}

	@Override
	public CategoriePlat create(CategoriePlat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoriePlat update(CategoriePlat obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CategoriePlat obj) {
		// TODO Auto-generated method stub
		
	}

	
}
