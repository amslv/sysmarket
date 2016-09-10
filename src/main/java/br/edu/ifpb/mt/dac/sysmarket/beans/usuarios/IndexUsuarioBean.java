/**
 * 
 */
package br.edu.ifpb.mt.dac.sysmarket.beans.usuarios;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Usuario;
import br.edu.ifpb.mt.dac.sysmarket.service.UsuarioService;

@Named(value="indexUsuario")
@ViewScoped
public class IndexUsuarioBean extends AbstractManageBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5071175218467348538L;
	@Inject
	private UsuarioService service;
	
	@Inject
	private Usuario usuario;
	
	private List<Usuario> usuarios;
	
	@PostConstruct
	public void init() {
		usuarios = service.getAll();
	}

	public void excluir(Usuario user){
		try {
			service.remove(user);
			atualizar();
			showFlashMessageInfo("Usuário excluído!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atualizar() {
		usuarios = service.getAll();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
