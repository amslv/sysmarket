package br.edu.ifpb.mt.dac.sysmarket.beans.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.util.AbstractManagedBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Cliente;
import br.edu.ifpb.mt.dac.sysmarket.entities.ItemProduto;
import br.edu.ifpb.mt.dac.sysmarket.entities.Pedido;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;
import br.edu.ifpb.mt.dac.sysmarket.service.ClienteService;
import br.edu.ifpb.mt.dac.sysmarket.service.PedidoService;
import br.edu.ifpb.mt.dac.sysmarket.service.ProdutoService;

@ViewScoped
@Named
public class EditPedidoBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7084789732538455286L;
	
	private List<Produto> produtosDisponiveis;
	private List<Produto> produtosSelecionados;
	private List<ItemProduto> itensDoPedido;
	private List<Cliente> clientesCadastrados;
	
	@Inject private Cliente clienteSelecionado;
	private Pedido pedido;
	
	private Double valorTotalDoPedido;
	
	@Inject private ProdutoService produtoService;
	@Inject private ClienteService clienteService;
	@Inject private PedidoService pedidoService;
	
	/**
	 * 
	 */
	
	@PostConstruct
	public void init() {
		produtosDisponiveis = produtoService.getAll();
		clientesCadastrados = clienteService.getAll();
	}
	
	public void preRenderView() {
		if (pedido == null) {
			pedido = new Pedido();
			pedido.setDataPedido(new Date());
			pedido.setItens(new ArrayList<>());
			pedido.setCliente(new Cliente());
			pedido.setValor(valorTotalDoPedido);
		}
	}
	
	public String savePedido() {
		try {
			pedido.setValor(calcularValorPedido());
			pedidoService.save(pedido);
			showFlashMessageInfo("Pedido salvo com sucesso");
		} catch (Exception e) {
			showFlashMessageError("Falha ao salvar pedido");
		}
		return "index_pedidos?faces-redirect=true";
	}
	
	public void selecionarProdutos() {
		if (itensDoPedido == null) {
			itensDoPedido = new ArrayList<>();
		}
		ItemProduto itemProduto = null;
		for (Produto produto : produtosSelecionados) {
			itemProduto = new  ItemProduto();
			itemProduto.setProduto(produto);
			itemProduto.setPedido(pedido);
			itemProduto.setQuantidade(1);
			itemProduto.setValor(produto.getValorVenda());
			
			if (!itensDoPedido.contains(itemProduto)) {
				itensDoPedido.add(itemProduto);
			} else {
				for (ItemProduto iProduto : itensDoPedido) {
					if (iProduto.getProduto().getId() == itemProduto.getProduto().getId()) {
						iProduto.setQuantidade(iProduto.getQuantidade()+1);
					}
				}
			}
		}
		pedido.setItens(itensDoPedido);
		calcularValorPedido();
	}
	
	public Double calcularValorPedido() {
		valorTotalDoPedido = 0d;
		for (ItemProduto itemProduto : itensDoPedido) {
			valorTotalDoPedido += itemProduto.getValor()*itemProduto.getQuantidade();
		}
		return valorTotalDoPedido;
	}
	
	public List<Produto> getProdutosDisponiveis() {
		return produtosDisponiveis;
	}
	public void setProdutosDisponiveis(List<Produto> produtosDisponiveis) {
		this.produtosDisponiveis = produtosDisponiveis;
	}
	public List<Produto> getProdutosSelecionados() {
		return produtosSelecionados;
	}
	public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}
	public List<ItemProduto> getItensDoPedido() {
		return itensDoPedido;
	}
	public void setItensDoPedido(List<ItemProduto> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}
	public List<Cliente> getClientesCadastrados() {
		return clientesCadastrados;
	}
	public void setClientesCadastrados(List<Cliente> clientesCadastrados) {
		this.clientesCadastrados = clientesCadastrados;
	}
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}
	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Double getValorTotalDoPedido() {
		return valorTotalDoPedido;
	}
	public void setValorTotalDoPedido(Double valorTotalDoPedido) {
		this.valorTotalDoPedido = valorTotalDoPedido;
	}
}