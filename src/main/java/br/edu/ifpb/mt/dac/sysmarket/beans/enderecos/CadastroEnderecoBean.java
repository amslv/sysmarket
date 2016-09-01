package br.edu.ifpb.mt.dac.sysmarket.beans.enderecos;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.entities.Estado;
import br.edu.ifpb.mt.dac.sysmarket.service.EnderecoService;
import br.edu.ifpb.mt.dac.sysmarket.service.EstadoService;

@Named("enderecoEdit")
@RequestScoped
public class CadastroEnderecoBean extends AbstractManageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6953503009804860785L;
	private Endereco endereco;
	@Inject
	private EnderecoService service;
	private List<Estado> estados;
	@Inject
	private EstadoService estadoService;

	@PostConstruct
	public void init() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		estados = estadoService.getAll();
	}

	public void saveEndereco() {
		try {
			service.save(endereco);
			showFlashMessageInfo("Endereço cadastrado com sucesso!");
		} catch(Exception ex) {
			showFlashMessageError("Falha ao cadastrar endereco!");
		}
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
