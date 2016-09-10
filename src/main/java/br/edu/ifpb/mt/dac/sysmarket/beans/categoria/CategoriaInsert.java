package br.edu.ifpb.mt.dac.sysmarket.beans.categoria;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Categoria;
import br.edu.ifpb.mt.dac.sysmarket.service.CategoriaService;

@Named
@ViewScoped
public class CategoriaInsert extends AbstractManageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8559018315064616512L;

	@Inject
	private CategoriaService service;
	private List<Categoria> categorias;
	@Inject
	private Categoria categoria;

	@PostConstruct
	public void start() {
		try {			
			atualiza();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String insertCategoria() {
		try {
			service.save(categoria);
			atualiza();
			showFlashMessageInfo("Categoria Salva!");
		} catch (Exception e) {			
			e.printStackTrace();
			if(e.toString().contains("ConstraintViolationException")){
				showFlashMessageError("Essa Categoria já existe!");
			}else{
				showFlashMessageError("Erro ao tentar salvar a categoria!");
			}
			return "insert_cat";
		}	
		return "/insert_cat?faces-redirect=true";
	}

	public void excluir(Categoria categoria){
		try {
			service.remove(categoria);
			atualiza();
			showFlashMessageInfo("Categoria excluida!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void atualiza() throws Exception {
		categorias = service.getAll();
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



}
