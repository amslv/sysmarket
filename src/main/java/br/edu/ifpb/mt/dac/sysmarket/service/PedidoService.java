package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.PedidoDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;
import br.edu.ifpb.mt.dac.sysmarket.entities.TipoPagamento;

public class PedidoService implements IService<Pedido> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2804992837874931137L;

	@Inject
	private PedidoDAO dao;
	
	public void save(Pedido t) {
		dao.save(t);
	}

	public Pedido update(Pedido t) {
		return dao.update(t);
	}

	public void remove(Pedido t) {
		dao.delete(t);
	}

	public Pedido getById(Long id) {
		return dao.getByID(id);
	}

	public List<Pedido> getAll() {
		return dao.getAll();
	}

	public void updatePedido(Pedido pedido, TipoPagamento pagamento) {		
	}

}
