package model.dao;

import model.dao.impl.DepartmentDaoImpl;
import model.dao.impl.SellerDaoImpl;
import model.entities.Department;
import model.entities.Seller;

public class DaoFactory {

	public static GenericDao<Seller, Integer> getSellerDao(){
		return new SellerDaoImpl("jpa-hibernate-mysql");
	}
	
	public static GenericDao<Department, Integer> getDepartmentDao(){
		return new DepartmentDaoImpl("jpa-hibernate-mysql");
	}
}
