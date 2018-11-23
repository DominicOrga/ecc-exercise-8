package com.ecc.exercise8;

public interface IGenericDAO<T> {

	T get(long id, String... fetchedColumns);
	T getAll(long id, String... fetchedColumns);

	void create(T entity);
	void update(T entity);
	void delete(T entity);
	void delete(T entity, long id);
}