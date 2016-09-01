package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.FornecedorDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;

public class FornecedorService implements IService<Fornecedor> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3049929107844211743L;
	@Inject
	private FornecedorDAO dao;

	public void save(Fornecedor t) {
		dao.save(t);
	}

	public Fornecedor update(Fornecedor t) {
		return dao.update(t);
	}

	public void remove(Fornecedor t) {
		dao.delete(t);
	}

	public Fornecedor getById(Long id) {
		return dao.getByID(id);
	}

	public List<Fornecedor> getAll() {
		return dao.getAll();
	}

	public List<Fornecedor> getByNomeFantasia(String nomeFantasia) {
		return dao.getByNomeFantasia(nomeFantasia);
	}
}
