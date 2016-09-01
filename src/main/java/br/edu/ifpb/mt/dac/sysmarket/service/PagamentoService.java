package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.PagamentoDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pagamento;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class PagamentoService implements IService<Pagamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8831233455001395109L;
	
	@Inject
	private PagamentoDAO dao;

	@TransacionalCdi
	public void save(Pagamento t) {
		dao.save(t);
	}

	@TransacionalCdi
	public Pagamento update(Pagamento t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(Pagamento t) {
		dao.delete(t);
	}

	public Pagamento getById(Long id) {
		return dao.getByID(id);
	}

	public List<Pagamento> getAll() {
		return dao.getAll();
	}

}
