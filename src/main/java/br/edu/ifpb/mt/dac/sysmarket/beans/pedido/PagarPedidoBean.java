package br.edu.ifpb.mt.dac.sysmarket.beans.pedido;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.util.AbstractManagedBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pagamento;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;
import br.edu.ifpb.mt.dac.sysmarket.entities.TipoPagamento;
import br.edu.ifpb.mt.dac.sysmarket.service.PagamentoService;

@Named
@ViewScoped
public class PagarPedidoBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5947895300274656720L;
	
	private Pagamento pagamento;
	
	@Inject private Pedido pedido;
	
	@Inject private PagamentoService service;

	@PostConstruct
	public void init() {
		if (pagamento == null) {
			pagamento = new Pagamento();
		}
	}
	
	public String pagarPedido() {
		try {
			pagamento.setValor(pedido.getValor());
			pagamento.setPedido(pedido);
			service.save(pagamento);
			showFlashMessageInfo("Pagamento efetuado com sucesso!");
		} catch (Exception e) {
			showFlashMessageInfo("Falha ao efetuar pagamento!");
		}
		return "index_pedidos?faces-redirect=true";
	}
	
	public TipoPagamento[] tipos() {
		return TipoPagamento.values();
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
