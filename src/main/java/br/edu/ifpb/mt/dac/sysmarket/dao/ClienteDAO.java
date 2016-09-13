package br.edu.ifpb.mt.dac.sysmarket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.mt.dac.sysmarket.entities.Cliente;

public class ClienteDAO extends AbstractDAO<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6634711896781673975L;

	public List<Cliente> getByName(String name) {
		EntityManager em = getEntityManager();
		List<Cliente> list = new ArrayList<Cliente>();
		list = (List<Cliente>) em.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE '%"+name+"%'", Cliente.class).getResultList();
		return list;
	}
	
	public Long getTotalClientes() {
		Query query = getEntityManager().createNamedQuery("Cliente.totalClientesNoSistema");
		Long result = (Long) query.getSingleResult();
		return result;
	}
}
