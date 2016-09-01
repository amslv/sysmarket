package br.edu.ifpb.mt.dac.sysmarket.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class DAO {

	@Inject
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
}