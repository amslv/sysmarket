package br.edu.ifpb.mt.dac.sysmarket.beans.util;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.service.ClienteService;
import br.edu.ifpb.mt.dac.sysmarket.service.FornecedorService;
import br.edu.ifpb.mt.dac.sysmarket.service.ProdutoService;

@Named
@ViewScoped
public class DashboardBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1123618695367794752L;

	@Inject
	private ProdutoService produtoService;
	@Inject
	private ClienteService clienteService;
	@Inject
	private FornecedorService fornecedorService;
	private Long numProdutos;
	private Long numClientes;
	private Long numFornecedores;
	
	@PostConstruct
	public void init() {
		calcularTotalDeProdutos();
		calcularTotalDeClientes();
		calcularTotalDeFornecedores();
	}
	
	public void calcularTotalDeProdutos() {
		numProdutos = 0l;
		numProdutos = produtoService.getTotalProdutos();
	}
	
	public void calcularTotalDeClientes() {
		numClientes = 0l;
		numClientes = clienteService.getTotalClientes();
	}
	
	public void calcularTotalDeFornecedores() {
		numFornecedores = 0l;
		numFornecedores = fornecedorService.getTotalFornecedores();
	}
	
	public Long getNumProdutos() {
		return numProdutos;
	}

	public void setNumProdutos(Long numProdutos) {
		this.numProdutos = numProdutos;
	}
	
	public Long getNumClientes() {
		return numClientes;
	}
	
	public void setNumClientes(Long numClientes) {
		this.numClientes = numClientes;
	}
	
	public Long getNumFornecedores() {
		return numFornecedores;
	}
	
	public void setNumFornecedores(Long numFornecedores) {
		this.numFornecedores = numFornecedores;
	}
}
