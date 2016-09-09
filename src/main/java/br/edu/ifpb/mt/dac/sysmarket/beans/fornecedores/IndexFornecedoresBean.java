package br.edu.ifpb.mt.dac.sysmarket.beans.fornecedores;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;
import br.edu.ifpb.mt.dac.sysmarket.service.FornecedorService;

@Named(value="indexFornecedores")
@RequestScoped
public class IndexFornecedoresBean extends AbstractManageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1192971384398103537L;
	
	private List<Fornecedor> fornecedores;
	
	@Inject
	private FornecedorService service;
	
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
}
