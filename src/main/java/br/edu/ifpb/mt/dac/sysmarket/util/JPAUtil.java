package br.edu.ifpb.mt.dac.sysmarket.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	@Produces
	@ApplicationScoped
	public EntityManagerFactory criarEMF() {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("sysmarket");
		} catch (Throwable t) {
			try {
				throw t;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return emf;
	}

	@Produces
	@RequestScoped
	public EntityManager criarEM(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

	public void fecharEM(@Disposes EntityManager em) {
		em.close();
	}

	public void fecharEMF(@Disposes EntityManagerFactory emf) {
		emf.close();
	}

}
