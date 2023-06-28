package model.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import model.dao.GenericDao;
import model.dao.exceptions.DbException;
import model.entities.Department;

public class DepartmentDaoImpl implements GenericDao<Department, Integer>{
	
	 private EntityManagerFactory entityManagerFactory;
	 private EntityManager entityManager;
	
	public DepartmentDaoImpl(String persistenceUnit) {
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Department> findAll() {
		return entityManager.createQuery("SELECT u FROM Department u", Department.class).getResultList();
	}

	@Override
	public Department findById(Integer id) {
		return entityManager.find(Department.class, id);
	}

	@Transactional
	@Override
	public void insert(Department obj) {
		try {
		entityManager.getTransaction().begin();
		entityManager.persist(obj);
		entityManager.getTransaction().commit();
		
		} catch(Exception e) {
			throw new DbException(e.getMessage());
			
		}
	}

	@Transactional
	@Override
	public void update(Department obj) {
		try {
		entityManager.getTransaction().begin();
		entityManager.merge(obj);
		entityManager.getTransaction().commit();
		
		} catch(Exception e) {
			throw new DbException(e.getMessage());
		}
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		try {
		entityManager.getTransaction().begin();
		Department obj = findById(id);
		entityManager.remove(obj);
		entityManager.getTransaction().commit();
		
		} catch(Exception e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public void removeAll() {
		List<Department> objs = findAll();
		if (objs != null) {
			for (Department obj : objs) {
				entityManager.getTransaction().begin();
				entityManager.remove(obj);
				entityManager.getTransaction().commit();
			}
		} else {
			return;
		}
	}
	
	@Override
	public void closeConnection() {
		entityManager.close();
		entityManagerFactory.close();
	}


}
