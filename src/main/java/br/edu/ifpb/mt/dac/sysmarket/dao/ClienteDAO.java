package br.edu.ifpb.mt.dac.sysmarket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.ifpb.mt.dac.sysmarket.entities.Cliente;

public class ClienteDAO extends AbstractDAO<Cliente> {

	public List<Cliente> getByName(String name) {
		EntityManager em = getEntityManager();
		List<Cliente> list = new ArrayList<Cliente>();
		list = (List<Cliente>) em.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE '%"+name+"%'", Cliente.class).getResultList();
		return list;
	}
}
