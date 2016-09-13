package br.edu.ifpb.mt.dac.sysmarket.beans.pedido;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.util.AbstractManagedBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;
import br.edu.ifpb.mt.dac.sysmarket.service.PedidoService;

@Named
@RequestScoped
public class IndexPedidoBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2706873427616176253L;
	@Inject
	private Pedido pedidoSelecionado;
	private List<Pedido> pedidos;
	@Inject
	private PedidoService service;
	
	
	public void preRenderView() {
		atualizar();
	}
	
	public void deletePedido() {
		try {
			if (pedidoSelecionado.getId() != null) {
				service.remove(pedidoSelecionado);
				atualizar();
				showFlashMessageInfo("Pedido excluído.");
			}
		} catch (Exception e) {
			showFlashMessageError("Falha ao excluir pedido.");
		}
	}
	
	public void atualizar() {
		pedidos = service.getAll();
	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
