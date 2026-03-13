package Application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.ObjectDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		
		ObjectDao sellerDao = DaoFactory.createSellerDao();
		
		
		Seller seller = new Seller(21, "bob", "bob@gmail.com", new Date(), 3000.0, obj);
	}

}
