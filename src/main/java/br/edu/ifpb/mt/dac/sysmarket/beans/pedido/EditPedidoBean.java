package br.edu.ifpb.mt.dac.sysmarket.beans.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Cliente;
import br.edu.ifpb.mt.dac.sysmarket.entities.ItemProduto;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pagamento;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;
import br.edu.ifpb.mt.dac.sysmarket.entities.TipoPagamento;
import br.edu.ifpb.mt.dac.sysmarket.service.ClienteService;
import br.edu.ifpb.mt.dac.sysmarket.service.PedidoService;
import br.edu.ifpb.mt.dac.sysmarket.service.ProdutoService;

@ViewScoped
@Named
public class EditPedidoBean extends AbstractManageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7084789732538455286L;
	private List<Produto> produtosSelecionados;
	private List<Produto> produtos;
	private double valorTotal;
	@Inject
	private ItemProduto itemSelecionado;
	private List<ItemProduto> itensDoPedido;
	private List<Cliente> clientes;
	@Inject
	private Cliente cliente;
	@Inject
	private Pedido pedido;
	@Inject
	private Pagamento pagamento;
	@Inject
	private ClienteService clienteService;
	@Inject
	private ProdutoService produtoService;
	@Inject
	private PedidoService pedidoService;
	
	@PostConstruct
	public void init() {
		clientes = clienteService.getAll();
		produtos = produtoService.getAll();
	}


	public void selecionarProdutos() {		
		addProduto();
	}

	public void selecionaCliente() {

		showFlashMessageInfo(cliente.getNome()+" selecionado");
	}

	public void removeItem() {
		showFlashMessageInfo(itemSelecionado.getProduto().getNome() + " removido");
		itensDoPedido.remove(itemSelecionado);
		getValorTotal();
	}

	public String savePedido() {
		try {
			if (isEdicaoPedido()) {
				if(cliente.getId()==0)
					throw new Exception("Selecione um cliente");
				if(itensDoPedido.size()==0)
					throw new Exception("O pedido está vazio!");
				pedido.setItens(getItemProduto());
				pedido.setDataPedido(new Date());
				pedido.setCliente(cliente);		
				pagamento.setValor(valorTotal);
				pedido.setPagamento(pagamento);
				pedido.setValor(valorTotal);
				pedidoService.update(pedido);
				showFlashMessageInfo("Pedido atualizado!");
			} else {
				if(cliente.getId()==0)
					throw new Exception("Selecione um cliente");
				if(itensDoPedido.size()==0)
					throw new Exception("O pedido está vazio!");
				pedido.setItens(getItemProduto());
				pedido.setDataPedido(new Date());
				pedido.setCliente(cliente);		
				pagamento.setValor(valorTotal);
				pedido.setPagamento(pagamento);
				pedido.setValor(valorTotal);
				pedidoService.update(pedido);
				showFlashMessageInfo("Pedido salvo!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.toString().contains("ConstraintViolationException")) {
				showFlashMessageError("Essa Pedido já existe!");
			} else if(e.toString().contains("Selecione um cliente")||e.toString().contains("O pedido está vazio!")){
				showFlashMessageError(e.getMessage());
			}else{
				showFlashMessageError("Erro ao tentar salvar o pedido!");
			}
			return null;
		}
		return "lista_pedidos?faces-redirect=true";
	}

	public Boolean isEdicaoPedido() {
		return pedido.getId() != null;
	}
	
	public List<ItemProduto> getItensDoPedido() {
		return itensDoPedido;
	}
	
	public void setItensDoPedido(List<ItemProduto> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}

	public List<Produto> getProdutosSelecionados() {
		return produtosSelecionados;
	}

	public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}

	public List<Produto> getProdutos() {
		try {
			produtos = produtoService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setProdutoSelecionado(ItemProduto itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public List<ItemProduto> getItemProduto() {
		if (itensDoPedido == null) {
			itensDoPedido = new ArrayList<>();
		}

		for(ItemProduto item:itensDoPedido){
			item.setValorTotal(item.getProduto().getValorVenda()*item.getQuantidade());
		}

		return itensDoPedido;
	}

	public ItemProduto getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemProduto itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	private void addProduto() {
		if (itensDoPedido == null) {
			itensDoPedido = new ArrayList<>();
		}
		ItemProduto it = null;
		for (Produto p : produtosSelecionados) {
			it = new ItemProduto();
			it.setProduto(p);
			it.setPedido(pedido);
			it.setValor(p.getValorVenda());
			it.setQuantidade(1);
		}
		if (!itensDoPedido.contains(it)) {
			itensDoPedido.add(it);
		}else{
			for (ItemProduto item : getItemProduto()) {
				if (it.getProduto().getId() == item.getProduto().getId()) {
					item.setQuantidade(item.getQuantidade()+1);
				}
			}
		}
		getValorTotal();
	}

	public double getValorTotal() {
		valorTotal = 0;
		for (ItemProduto item : getItemProduto()) {
			valorTotal += item.getValor()*item.getQuantidade();
		}

		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public TipoPagamento[] tipos() {
		return TipoPagamento.values();
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Produto editado",
				((ItemProduto) event.getObject()).getProduto().getNome());

		ItemProduto item = ((ItemProduto) event.getObject());
		for (ItemProduto it : getItemProduto()) {
			if (it.getProduto().getId() == item.getProduto().getId()) {
				it = item;
			}
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((ItemProduto) event.getObject()).getId() + "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void setItemProduto(List<ItemProduto> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}
}