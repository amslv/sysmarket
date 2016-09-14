package br.edu.ifpb.mt.dac.sysmarket.beans.util;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;
import br.edu.ifpb.mt.dac.sysmarket.service.PedidoService;

@Named
@ViewScoped
public class PedidoFieldBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4653874488151064936L;
	private List<Pedido> pedidos;
	private Pedido pedido;
	@Inject
	private PedidoService service;
	
	@PostConstruct
	public void init() {
		pegarUltimoPedido();
	}
	
	public void pegarUltimoPedido() {
		pedido = null;
		pedidos = service.getAll();
		pedido = pedidos.get(pedidos.size()-1);
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
