package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.ProdutoDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class ProdutoService implements IService<Produto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8831405871246868149L;
	
	@Inject
	private ProdutoDAO dao;
	
	@TransacionalCdi
	public void save(Produto t) {
		dao.save(t);
	}

	@TransacionalCdi
	public Produto update(Produto t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(Produto t) {
		dao.delete(t);
	}

	public Produto getById(Long id) {
		return dao.getByID(id);
	}

	public List<Produto> getAll() {
		return dao.getAll();
	}
	
	public List<Produto> getByName(String name) {
		return dao.getByName(name);
	}
	
	public List<Produto> getBySku(String sku) {
		return dao.getBySku(sku);
	}
	
	public Long getTotalProdutos() {
		return dao.getTotalProdutos();
	}
}
