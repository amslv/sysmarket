package br.edu.ifpb.mt.dac.sysmarket.beans.fornecedores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.entities.Estado;
import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;
import br.edu.ifpb.mt.dac.sysmarket.service.EnderecoService;
import br.edu.ifpb.mt.dac.sysmarket.service.EstadoService;
import br.edu.ifpb.mt.dac.sysmarket.service.FornecedorService;
import br.edu.ifpb.mt.dac.sysmarket.service.ProdutoService;

@Named
@ViewScoped
public class EditFornecedorBean extends AbstractManageBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7159694750976020501L;
	
	private Fornecedor fornecedor;
	
	private List<Estado> estados;
	
	@Inject
	private FornecedorService service;
	
	
	
	@Inject
	private EnderecoService enderecoService;
	
	@Inject
	private EstadoService dao;
	
	private Endereco enderecoSelecionado;
	private List<Endereco> enderecos;
	
	private DualListModel<Produto> produtosDisponíveis;
	private List<Produto> produtos = new ArrayList<Produto>();
	private List<Produto> produtosSelecionados = new ArrayList<Produto>();
	
	@Inject
	private ProdutoService pService;
	
	@PostConstruct
	public void init() {
		enderecoSelecionado = new Endereco();
		enderecoSelecionado.setEstado(new Estado());
		produtos = pService.getAll();
		estados = dao.getAll();
		enderecos = enderecoService.getAll();
		produtosDisponíveis = new DualListModel<Produto>(produtos, produtosSelecionados);
	}
	
	public void preRenderView() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
			fornecedor.setEndereco(new Endereco());
		}
	}
	
	public void saveFornecedor() {
		try {
			if (isEdicaoFornecedor()) {
				service.update(fornecedor);
				showFlashMessageInfo("Fornecedor atualizado com sucesso.");
			} else {
				service.save(fornecedor);
				showFlashMessageInfo("Fornecedor salvo com sucesso.");
			}
		} catch (Exception e) {
			showFlashMessageError("Falha ao salvar fornecedor!");
		}
	}
	
	public Boolean isEdicaoFornecedor() {
		return fornecedor.getId() != null;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	
	public List<Produto> getProdutosSelecionados() {
		return produtosSelecionados;
	}
	
	public void setProdutosSelecionados(List<Produto> produtosSelecionados) {
		this.produtosSelecionados = produtosSelecionados;
	}
	
	public DualListModel<Produto> getProdutosDisponíveis() {
		return produtosDisponíveis;
	}
	
	public void setProdutosDisponíveis(DualListModel<Produto> produtosDisponíveis) {
		this.produtosDisponíveis = produtosDisponíveis;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}
}
