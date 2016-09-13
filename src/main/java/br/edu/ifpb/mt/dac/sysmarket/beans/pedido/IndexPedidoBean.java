package br.edu.ifpb.mt.dac.sysmarket.beans.pedido;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.util.AbstractManagedBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;
import br.edu.ifpb.mt.dac.sysmarket.service.PedidoService;

@Named
@ViewScoped
public class IndexPedidoBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2706873427616176253L;
	private List<Pedido> pedidos;
	@Inject
	private PedidoService service;
	
	@PostConstruct
	public void init() {
		pedidos = service.getAll();
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
