package br.edu.ifpb.mt.dac.sysmarket;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import br.edu.ifpb.mt.dac.sysmarket.entities.TipoUsuario;
import br.edu.ifpb.mt.dac.sysmarket.entities.Usuario;
import br.edu.ifpb.mt.dac.sysmarket.util.PasswordCript;

public class PopularBD {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("daca-jaas");
			em = emf.createEntityManager();

			tx = em.getTransaction();
			tx.begin();

			List<Usuario> usuarios = new ArrayList<Usuario>();

			usuarios.add(getUsuarioVisitante());
			usuarios.add(getUsuarioAdmin());

			for (Usuario user : usuarios) {
				user.setSenha(PasswordCript.criptografarSenha(user));
				em.persist(user);
			}

			tx.commit();
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}

	}

	private static Usuario getUsuarioAdmin() {
		Usuario user = new Usuario();
		user.setNome("Fulano");
		user.setSobrenome("de Tal");
		user.setUsername("admin1");
		user.setEmail("admin@admin.com");
		user.setSenha("1234567");
		user.setTipo(TipoUsuario.ADMIN);
		return user;
	}

	private static Usuario getUsuarioVisitante() {
		Usuario user = new Usuario();
		user.setNome("Sicrano");
		user.setSobrenome("de Tal");
		user.setUsername("estoquista1");
		user.setEmail("estoquista@estoquista.com");
		user.setSenha("123456");
		user.setTipo(TipoUsuario.ESTOQUISTA);
		return user;
	}
}
