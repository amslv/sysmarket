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
	private Produto produto;
	@Inject
	private ProdutoService service;
	
	@PostConstruct
	public void init() {
		produtos = service.getAll();
	}
	
	public void excluir(Produto produto){
		try {
			service.remove(produto);
			atualizar();
			showFlashMessageInfo("Produto excluido!");
		} catch (Exception e) {
			e.printStackTrace();
			showFlashMessageError("Falha ao excluir produto");
		}
	}

	private void atualizar() {
		produtos = service.getAll();
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
