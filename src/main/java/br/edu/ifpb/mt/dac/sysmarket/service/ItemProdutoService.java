package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.ItemProdutoDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.ItemProduto;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class ItemProdutoService implements IService<ItemProduto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8952704664384959202L;
	
	@Inject
	private ItemProdutoDAO dao;

	@TransacionalCdi
	public void save(ItemProduto t) {
		dao.save(t);
	}

	@TransacionalCdi
	public ItemProduto update(ItemProduto t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(ItemProduto t) {
		dao.delete(t);
	}

	public ItemProduto getById(Long id) {
		return dao.getByID(id);
	}

	public List<ItemProduto> getAll() {
		return dao.getAll();
	}

}
