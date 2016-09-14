package br.edu.ifpb.mt.dac.sysmarket.beans.util;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;
import br.edu.ifpb.mt.dac.sysmarket.service.FornecedorService;

@Named
@ViewScoped
public class FornecedorFieldBean implements Serializable {
	
	private List<Fornecedor> fornecedores;
	private Fornecedor fornecedor;
	@Inject
	private FornecedorService service;
	
	@PostConstruct
	public void init() {
		pegarUltimoFornecedor();
	}
	
	public void pegarUltimoFornecedor() {
		fornecedor = null;
		fornecedores = service.getAll();
		fornecedor = fornecedores.get(fornecedores.size()-1);
	}

	public List<Fornecedor> getFornecedors() {
		return fornecedores;
	}

	public void setFornecedors(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
