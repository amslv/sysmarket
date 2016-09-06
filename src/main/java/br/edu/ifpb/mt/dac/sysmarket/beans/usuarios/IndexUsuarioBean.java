/**
 * 
 */
package br.edu.ifpb.mt.dac.sysmarket.beans.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.AbstractManageBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Usuario;
import br.edu.ifpb.mt.dac.sysmarket.service.UsuarioService;

@Named
@ViewScoped
public class IndexUsuarioBean extends AbstractManageBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5071175218467348538L;
	@Inject
	private UsuarioService service;
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	public void init() {
		usuarios = service.getAll();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
