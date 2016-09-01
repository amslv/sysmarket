package br.edu.ifpb.mt.dac.sysmarket.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;

public class ProdutoDAO extends AbstractDAO<Produto> {

	private List<Produto> find(String where) {
		EntityManager manager = getEntityManager();
		List<Produto> list = new ArrayList<Produto>();
		list = (List<Produto>) manager.createQuery("SELECT p FROM Produto p " + where, Produto.class)
				.getResultList();
		return list;
	}

	public List<Produto> getByName(String nome) {
		String where = "WHERE p.nome like '%" + nome + "%'";
		return find(where);
	}
}
