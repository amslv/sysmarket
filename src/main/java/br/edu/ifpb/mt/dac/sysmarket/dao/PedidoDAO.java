package br.edu.ifpb.mt.dac.sysmarket.dao;

import javax.persistence.Query;

import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;

public class PedidoDAO extends AbstractDAO<Pedido>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5907849916760623101L;

	public Long getTotalPedidos() {
		Query query = getEntityManager().createNamedQuery("Pedido.totalPedidosDoSistema");
		Long result = (Long) query.getSingleResult();
		return result;
	}
}
