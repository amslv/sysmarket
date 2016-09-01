package br.edu.ifpb.mt.dac.sysmarket.beans.produtos;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Categoria;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;
import br.edu.ifpb.mt.dac.sysmarket.service.CategoriaService;
import br.edu.ifpb.mt.dac.sysmarket.service.ProdutoService;

@Named(value="produtoInsert")
@ViewScoped
public class ProdutoEditBean extends AbstractManageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4716204352054912915L;
	private Produto produto;
	private List<Categoria> categorias;
	@Inject
	private ProdutoService produtoService;
	@Inject
	private CategoriaService categoriaService;
	
	public void preRenderView() {
		if (produto == null) {
			produto = new Produto();
		}
		atualizar();
	}
	
	public String saveProduto() {
		try {
			if (isEdicaoProduto()) {
				produtoService.update(produto);
				showFlashMessageInfo("Produto atualizado com sucesso!");
			} else {
				produtoService.save(produto);
				showFlashMessageInfo("Produto salvo com sucesso!");
			}
		} catch (Exception e) {
			showFlashMessageError("Falha ao cadastrar produto!");
		}
		return "index_produtos?faces-redirect=true";
	}
	
	public Boolean isEdicaoProduto() {
		return produto.getId() != null;
	}
	
	public void atualizar() {
		categorias = categoriaService.getAll();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
