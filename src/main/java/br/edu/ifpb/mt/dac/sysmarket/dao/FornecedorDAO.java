package br.edu.ifpb.mt.dac.sysmarket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;

public class FornecedorDAO extends AbstractDAO<Fornecedor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1829367985985868093L;

	public List<Fornecedor> getByNomeFantasia(String nomeFantasia) {
		EntityManager em = getEntityManager();
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		list = (List<Fornecedor>) em.createQuery("SELECT f FROM Fornecedor f WHERE f.nomeFantasia LIKE '%" + nomeFantasia + "%'", Fornecedor.class)
				.getResultList();
		return list;
	}
}