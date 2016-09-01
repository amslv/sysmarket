package br.edu.ifpb.mt.dac.sysmarket.beans.clientes;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Cliente;
import br.edu.ifpb.mt.dac.sysmarket.service.ClienteService;

@Named
@ViewScoped
public class CadastroClienteBean extends AbstractManageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7508580427315268952L;
	@Inject
	private ClienteService service;
	private List<Cliente> clientes;
	@Inject
	private Cliente cliente;
	
	@PostConstruct
	public void start() {
		try {			
			atualizar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String saveCliente() {
		try {
			service.save(cliente);
			atualizar();
			showFlashMessageInfo("Cliente salvo com sucesso!");
		} catch (Exception e) {			
			e.printStackTrace();
			if(e.toString().contains("ConstraintViolationException")){
				showFlashMessageError("Essa cliente já existe!");
			}else{
			showFlashMessageError("Erro ao tentar salvar o cliente!");
			}
			return "cadastro_cliente";
		}	
		return "/cadastro_cliente?faces-redirect=true";
	}
	
	public void excluir(Cliente cliente){
		try {
			service.remove(cliente);
			atualizar();
			showFlashMessageInfo("Cliente excluído!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void atualizar() {
		clientes = service.getAll();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
