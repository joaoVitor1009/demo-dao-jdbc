package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface DepartmentDao extends GenericDao<Department> {
	Department findBySeller(Integer id);
}
