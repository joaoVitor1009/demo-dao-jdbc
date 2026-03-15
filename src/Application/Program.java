package Application;

import model.dao.GenericDao;
import model.dao.DaoFactory;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		
		GenericDao<Seller> sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: Seller find by id ===");
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
	}

}
