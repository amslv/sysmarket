package br.edu.ifpb.mt.dac.sysmarket.beans.fornecedores;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.entities.Fornecedor;
import br.edu.ifpb.mt.dac.sysmarket.entities.Produto;

@Named
@RequestScoped
public class ViewFornecedorBean {

	private Fornecedor fornecedor;
	
	public void preRenderView() {
		if (fornecedor == null) {
			fornecedor = new Fornecedor();
			fornecedor.setEndereco(new Endereco());
			fornecedor.setProdutos(new ArrayList<Produto>());
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}
