package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.EstadoDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Estado;

public class EstadoService implements IService<Estado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922915184809301838L;
	
	@Inject
	private EstadoDAO dao;

	public void save(Estado t) {
		dao.save(t);
	}

	public Estado update(Estado t) {
		return dao.update(t);
	}

	public void remove(Estado t) {
		dao.delete(t);
	}

	public Estado getById(Long id) {
		return dao.getByID(id);
	}

	public List<Estado> getAll() {
		return dao.getAll();
	}

}
