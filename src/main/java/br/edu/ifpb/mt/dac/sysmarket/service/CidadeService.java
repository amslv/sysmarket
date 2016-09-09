package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.CidadeDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Cidade;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class CidadeService implements IService<Cidade> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 164646872865217842L;
	@Inject
	private CidadeDAO dao;
	
	@TransacionalCdi
	public void save(Cidade t) {
		dao.save(t);
	}

	@TransacionalCdi
	public Cidade update(Cidade t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(Cidade t) {
		dao.delete(t);
	}

	public Cidade getById(Long id) {
		return dao.getByID(id);
	}

	public List<Cidade> getAll() {
		return dao.getAll();
	}
}
