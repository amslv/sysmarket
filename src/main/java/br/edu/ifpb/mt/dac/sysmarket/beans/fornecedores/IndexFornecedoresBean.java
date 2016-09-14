package br.edu.ifpb.mt.dac.sysmarket.beans.fornecedores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.util.AbstractManagedBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;
import br.edu.ifpb.mt.dac.sysmarket.service.FornecedorService;

@Named(value="indexFornecedores")
@RequestScoped
public class IndexFornecedoresBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1192971384398103537L;
	
	private List<Fornecedor> fornecedores;
	
	@Inject
	private Fornecedor fornecedor;
	
	@Inject
	private FornecedorService service;
	
	public void excluir(Fornecedor fornecedor){
		try {
			service.remove(fornecedor);
			atualizar();
			showFlashMessageInfo("Fornecedor excluido!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atualizar() {
		fornecedores = service.getAll();
	}

	@PostConstruct
	public void init() {
		fornecedores = service.getAll();
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}
