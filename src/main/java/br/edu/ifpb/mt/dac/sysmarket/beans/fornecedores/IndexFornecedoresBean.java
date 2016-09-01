package br.edu.ifpb.mt.dac.sysmarket.beans.fornecedores;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;
import br.edu.ifpb.mt.dac.sysmarket.service.FornecedorService;

@Named
@RequestScoped
public class IndexFornecedoresBean extends AbstractManageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1192971384398103537L;
	private List<Fornecedor> fornecedores;
	@Inject
	private Fornecedor fornecedorSelecionado;
	@Inject
	private FornecedorService service;
	private String filtro;

	public void preRenderView() {
		filtrar();
	}

	public void deleteFornecedor() {
		try {
			if (fornecedorSelecionado.getId() != null) {
				service.update(fornecedorSelecionado);
				filtrar();
			}
		} catch(Exception e) {
			showFlashMessageError("Falha ao excluir fornecedor.");
		}
	}

	public void filtrar() {
		if (filtro.isEmpty()) {
			fornecedores = service.getAll();
		} else {
			fornecedores = service.getByNomeFantasia(filtro);
		}
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}
}
