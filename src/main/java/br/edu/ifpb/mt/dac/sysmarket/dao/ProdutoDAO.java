package br.edu.ifpb.mt.dac.sysmarket.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;

public class ProdutoDAO extends AbstractDAO<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4882789693350797630L;

	public List<Produto> getByName(String nome) {
		Query query = getEntityManager().createNamedQuery("Produto.buscarPorNome");
		query.setParameter("nome", "%" + nome + "%");

		@SuppressWarnings("unchecked")
		List<Produto> result = query.getResultList();
		return result;
	}
	
	public List<Produto> getBySku(String sku) {
		Query query = getEntityManager().createNamedQuery("Produto.buscarPorSKU");
		query.setParameter("nome", "%" + sku + "%");
		@SuppressWarnings("unchecked")
		List<Produto> result = query.getResultList();
		return result;
	}
	
	public Long getTotalProdutos() {
		Query query = getEntityManager().createNamedQuery("Produto.totalProdutosSistema");
		Long result = (Long) query.getSingleResult();
		return result;
	}
}
