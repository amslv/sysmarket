package br.edu.ifpb.mt.dac.sysmarket.beans.usuarios;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.entities.Usuario;

@Named
@RequestScoped
public class ViewUsuarioBean {


	private Usuario usuario;
	
	public void preRenderView() {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setEndereco(new Endereco());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
