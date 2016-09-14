package br.edu.ifpb.mt.dac.sysmarket.beans.pedido;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;

@Named
@ViewScoped
public class ViewPedidoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -832626222129945408L;

	private Pedido pedido;
	
	@PostConstruct
	public void init() {
		if (pedido == null) {
			pedido = new Pedido();
		}
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
