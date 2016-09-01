package br.edu.ifpb.mt.dac.sysmarket.beans.pedido;

import java.util.Date;
import java.util.List;

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

@Named
@ViewScoped
public class EditPedidoBean extends AbstractManageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2656416744668815727L;
	@Inject
	private PedidoService service;
	@Inject
	private ProdutoService pService;
	@Inject
	private ClienteService cService;
	@Inject
	private Produto produto;
	private List<Produto> produtos;
	private Double valorTotal;
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

	public void selecionaProduto() {
		showFlashMessageInfo(produto.getNome());
		addProduto();
	}

	public void selecionaCliente() {

		showFlashMessageInfo(getCliente().getNome());
	}

	public void removeItem() {
		showFlashMessageInfo(itemSelecionado.getProduto().getNome() + " removido");

		pedido.getItens().remove(itemSelecionado);
		getValorTotal();
	}

	public String updatePedido() {
		try {

			if(getCliente().getId()==0)
				throw new Exception("Selecione um cliente");
			if(pedido.getItens().size()==0)
				throw new Exception("O pedido está vazio!");			
			pedido.setDataPedido(new Date());				
			pagamento.setValor(valorTotal);
			pedido.setValor(valorTotal);
			pagamento.getTipo();
			service.updatePedido(pedido, TipoPagamento.CASH);
			showFlashMessageInfo("Pedido salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			if (e.toString().contains("ConstraintViolationException")) {
				showFlashMessageError("Essa Pedido já existe!");
			} else if(e.toString().contains("Selecione um cliente")||e.toString().contains("O pedido está vazio!")){
				showFlashMessageError(e.getMessage());
			}else{
				showFlashMessageError("Erro ao tentar salvar a pedido!");
			}
			return null;
		}
		return "lista_pedidos?faces-redirect=true";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		try {
			produtos = pService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		try {
			clientes = cService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Cliente getCliente() {		
		return pedido.getCliente();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setProdutoSelecionado(ItemProduto itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public List<ItemProduto> getItensDoPedido() {
		
		for(ItemProduto item:pedido.getItens()){
			item.setValorTotal(item.getProduto().getValorVenda()*item.getQuantidade());
		}
		
		return pedido.getItens();
	}

	public ItemProduto getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemProduto itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	private void addProduto() {
		
		ItemProduto it = new ItemProduto();
		it.setProduto(produto);
		it.setPedido(pedido);
		it.setValor(produto.getValorVenda());
		it.setQuantidade(1);
		boolean contem = false;
		
		for (ItemProduto item : pedido.getItens()) {
			if (it.getProduto().getId() == item.getProduto().getId()) {
				contem = true;
			}
		}		
		if (!contem) {
			pedido.getItens().add(it);
		}else{
			for (ItemProduto item : pedido.getItens()) {
				if (it.getProduto().getId() == item.getProduto().getId()) {
					item.setQuantidade(item.getQuantidade()+1);
				}
			}
		}
		getValorTotal();
	}

	public double getValorTotal() {
		valorTotal = 0d;
		for (ItemProduto item : pedido.getItens()) {
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

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Produto Edited",
				((ItemProduto) event.getObject()).getProduto().getNome());
		ItemProduto item = ((ItemProduto) event.getObject());
		for (ItemProduto it : pedido.getItens()) {
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

	public void setItensDoPedido(List<ItemProduto> itensDoPedido) {
		pedido.setItens(itensDoPedido);
	}
}
