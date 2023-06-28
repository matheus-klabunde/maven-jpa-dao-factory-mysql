package model.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import model.dao.GenericDao;
import model.dao.exceptions.DbException;
import model.entities.Seller;

public class SellerDaoImpl implements GenericDao<Seller, Integer> {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public SellerDaoImpl(String PersistenceUnit) {
		entityManagerFactory = Persistence.createEntityManagerFactory(PersistenceUnit);
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public List<Seller> findAll() {
		return entityManager.createQuery("SELECT u FROM Seller u", Seller.class).getResultList();
	}

	@Override
	public Seller findById(Integer id) {
		return entityManager.find(Seller.class, id);
	}

	@Transactional
	@Override
	public void insert(Seller obj) {
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
	public void update(Seller obj) {
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
		Seller obj = findById(id);
		entityManager.remove(obj);
		entityManager.getTransaction().commit();
		
		} catch(Exception e) {
			throw new DbException(e.getMessage());
			
		}
	}
	
	@Override
	public void removeAll() {
		List<Seller> objs = findAll();
		if (objs != null) {
			for (Seller obj : objs) {
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
