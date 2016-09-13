package br.edu.ifpb.mt.dac.sysmarket.beans.produtos;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.util.AbstractManagedBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;
import br.edu.ifpb.mt.dac.sysmarket.service.ProdutoService;

@Named("indexProduto")
@RequestScoped
public class IndexProdutoBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7694566741426892147L;

	private List<Produto> produtos;
	@Inject
	private ProdutoService service;
	
	@PostConstruct
	public void init() {
		produtos = service.getAll();
	}


	public List<Produto> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}	
}
