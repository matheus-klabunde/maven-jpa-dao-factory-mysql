package application;

import model.dao.DaoFactory;
import model.dao.GenericDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department d1 = new Department(null, "Food");
		Department d2 = new Department(null, "Sports");
		
		Seller s1 = new Seller(null, "Matheus Klabunde", "matheus@gmail.com", 4500.0, d1);
		Seller s2 = new Seller(null, "Josh Turner", "josh@gmail.com", 9520.50, d1);
		Seller s3 = new Seller(null, "Jack Smith", "jack@gmail.com", 1800.0, d2);
		
		GenericDao<Seller, Integer> sellerDao = DaoFactory.getSellerDao();
		GenericDao<Department, Integer> departmentDao = DaoFactory.getDepartmentDao();
		
		sellerDao.removeAll();
		departmentDao.removeAll();
		
		System.out.println("\n=== TEST 1: Insert Departments ===");
		departmentDao.insert(d1);
		departmentDao.insert(d2);
		System.out.println("Department Insert completed!");
		
		System.out.println("\n=== TEST 2: Insert Sellers ===");
		sellerDao.insert(s1);
		sellerDao.insert(s2);
		sellerDao.insert(s3);
		System.out.println("Seller Insert completed!");
	
		departmentDao.closeConnection();
		sellerDao.closeConnection();
		
	}
}
