package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}


	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into department "
					   + "(Id, Name) "
					   + "values"
					   + "(?, ?)");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			
			int row = st.executeUpdate();
			if(row > 0) {
				System.out.println("Sucesso");
			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update department "
									   +"set Id = ?, Name = ? "
									   +"where Id = ?");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			st.setInt(3, obj.getId());
			
			int row = st.executeUpdate();
			
			if(row > 0) {
				System.out.println("Sucesso");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally  {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findBySeller(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select department.*, seller.Name as SellerName " 
					+ "from department inner join seller "
					+ "on seller.DepartmentId = department.Id " 
					+ "where seller.Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			
			if(rs.next()) {
				Department a = instantiateDepartment(rs);
				return a;
			} 
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			
			if(rows == 0) {
				throw new DbException("Id inexistente no banco");
			} else { 
				System.out.println("Sucesso!");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * "
					+ "FROM department "
					+ "WHERE Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			
			if(rs.next()) {
				Department a = instantiateDepartment(rs);
				return a;
			} 
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from department");
			
			List<Department> list = new ArrayList<Department>();
			
			rs = st.executeQuery();
			
			while(rs.next()) {
			Department a = instantiateDepartment(rs);
			list.add(a);	
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}
	
	
	private Seller instatiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("birthDate"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setDepartment(department);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}


}
