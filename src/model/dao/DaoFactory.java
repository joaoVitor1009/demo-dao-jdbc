package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static GenericDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}

	public static GenericDao createDepartmentDao() {
		return new DepartmentDaoJDBC();
	}
}
