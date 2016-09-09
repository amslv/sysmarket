package br.edu.ifpb.mt.dac.sysmarket.beans.fornecedores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Cidade;
import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.entities.Estado;
import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;
import br.edu.ifpb.mt.dac.sysmarket.service.CidadeService;
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
	
	private List<Cidade> cidades;
	
	@Inject
	private FornecedorService service;
	
	@Inject
	private EstadoService dao;
	
	@Inject
	private CidadeService cidadeService;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@Inject
	private ProdutoService pService;
	
	@PostConstruct
	public void init() {
		produtos = pService.getAll();
		estados = dao.getAll();
		cidades = cidadeService.getAll();
	}
	
	public void preRenderView() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
			fornecedor.setEndereco(new Endereco());
			fornecedor.setProdutos(new ArrayList<Produto>());
		}
	}
	
	public String saveFornecedor() {
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
		return "/index_fornecedores?faces-redirect=true";
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

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}
