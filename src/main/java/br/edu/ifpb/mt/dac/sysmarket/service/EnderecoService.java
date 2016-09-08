package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.EnderecoDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class EnderecoService implements IService<Endereco> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422626419768858768L;
	
	@Inject
	private EnderecoDAO dao;

	@TransacionalCdi
	public void save(Endereco t) {
		dao.save(t);
	}

	@TransacionalCdi
	public Endereco update(Endereco t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(Endereco t) {
		dao.delete(t);
	}

	public Endereco getById(Long id) {
		return dao.getByID(id);
	}

	public List<Endereco> getAll() {
		return dao.getAll();
	}

}
