package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.CategoriaDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Categoria;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class CategoriaService implements IService<Categoria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 164646872865217842L;
	@Inject
	private CategoriaDAO dao;
	
	@TransacionalCdi
	public void save(Categoria t) {
		dao.save(t);
	}

	@TransacionalCdi
	public Categoria update(Categoria t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(Categoria t) {
		dao.delete(t);
	}

	public Categoria getById(Long id) {
		return dao.getByID(id);
	}

	public List<Categoria> getAll() {
		return dao.getAll();
	}
}
