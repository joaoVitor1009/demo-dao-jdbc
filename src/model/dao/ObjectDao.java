package model.dao;

import java.util.List;

public interface ObjectDao {
	
	void insert(Object obj);
	void update(Object obj);
	void deleteById(Integer id);
	Object findById(Integer id);
	List<Object> findAll();
}
