package model.dao;

import java.util.List;

public interface GenericDao<T, G> {

	List<T> findAll();
	T findById(G id);
	void insert(T obj);
	void update(T obj);
	void delete(G id);
	void removeAll();
	void closeConnection();
}
