package br.edu.ifpb.mt.dac.sysmarket.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

public abstract class AbstractDAO<T extends Serializable> extends DAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8776857368657262989L;
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	protected AbstractDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public void save(T t) {
		EntityManager em = getEntityManager();
		try {
			em.persist(t);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw pe;
		} 
	}

	public T update(T t) {
		EntityManager em = getEntityManager();
		T resultado = t;
		try {
			resultado = em.merge(t);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw pe;
		} 
		return resultado;
	}

	public void delete(T t) {
		EntityManager em = getEntityManager();
		try {
			t = em.merge(t);
			em.remove(t);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw pe;
		} 
	}

	public T getByID(Serializable tId) {
		EntityManager em = getEntityManager();
		T resultado = null;
		try {
			resultado = em.find(persistentClass, tId);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw pe;
		} 
		return resultado;
	}

	public List<T> getAll() {
		EntityManager em = getEntityManager();
		List<T> resultado = null;
		try {
			TypedQuery<T> query = 
					em.createQuery(String.format("SELECT a FROM %s a", persistentClass.getName()), persistentClass);
			resultado = query.getResultList();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			throw pe;
		} 
		return resultado;
	}
}
