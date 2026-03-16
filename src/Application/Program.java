package Application;


import java.util.*;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		Department obj = new Department(1, "Books");
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao DepDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1: Seller find by id ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller); 
		
		System.out.println("\n=== TEST 2: Seller find by Department ===");
		Department dep = new Department(4, null);
		List<Seller> list = sellerDao.findByDepartment(dep);
		for( Seller x : list ) {
			System.out.println(x);
		} 
		
		System.out.println("\n=== TEST 3: Seller find by Department ===");
		list = sellerDao.findAll();
		for( Seller x : list ) {
			System.out.println(x);	
		} 
		System.out.println("\n=== TEST 4: insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep );
		sellerDao.insert(newSeller);
		System.out.println("Inserido, novo id: " + newSeller.getId());
		
		System.out.println("\n=== TEST 5: update ===");
		seller = sellerDao.findById(7);
		seller.setName("Martha Kent");
		sellerDao.update(seller);
		System.out.println("Atualizado vendedor id: " + seller.getId());
		
		/* System.out.println("\n=== TEST 6: delete ===");
		System.out.print("Entre com Id para deletar: ");
		sellerDao.deleteById(sc.nextInt()); */
		
		System.out.println("\n=== TEST 7: Department findall");
		List<Department> listDepartment = DepDao.findAll();
		for(Department x :listDepartment) {
			System.out.println(x);
		}
		System.out.println("\n=== TEST 8: Department find by Id");
		Department a = DepDao.findById(1);
		System.out.println(a);
		
		System.out.println("\n=== TEST 9: Department insert");
		Department b = new Department(16, "D3");
		DepDao.insert(b);
		
		System.out.println("\n=== TEST 10: Department update");
		Department c = new Department(9, "Kitchen");
		DepDao.update(c);
		
		System.out.println("\n=== TEST 10: Department find by seller");
		Department g =DepDao.findBySeller(newSeller.getId());
		System.out.println(g);
		
		System.out.println("\n=== TEST 6: Department delete ===");
		System.out.print("Entre com Id para deletar: ");
		DepDao.deleteById(15);
	}

}
