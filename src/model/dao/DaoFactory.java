package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static ObjectDao createSellerDao() {
		return new SellerDaoJDBC();
	}

	public static ObjectDao createDepartmentDao() {
		return new DepartmentDaoJDBC();
	}
}
