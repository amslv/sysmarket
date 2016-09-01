package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.ClienteDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Cliente;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class ClienteService implements IService<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1188547561617802613L;
	@Inject
	private ClienteDAO dao;
	
	@TransacionalCdi
	public void save(Cliente t) {
		dao.save(t);
	}

	@TransacionalCdi
	public Cliente update(Cliente t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(Cliente t) {
		dao.delete(t);
	}

	public Cliente getById(Long id) {
		return dao.getByID(id);
	}

	public List<Cliente> getAll() {
		return dao.getAll();
	}
	
	public List<Cliente> getByName(String name) {
		return dao.getByName(name);
	}
}
