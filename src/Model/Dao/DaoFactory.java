package Model.Dao;

import Model.Dao.Impl.ClienteDaoJDBC;
import db.DB;

public class DaoFactory {

	public static ClientDao createClientDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
}
