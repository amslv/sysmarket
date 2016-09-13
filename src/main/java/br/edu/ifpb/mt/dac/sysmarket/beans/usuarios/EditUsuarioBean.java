package br.edu.ifpb.mt.dac.sysmarket.beans.usuarios;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.mt.dac.sysmarket.beans.util.AbstractManagedBean;
import br.edu.ifpb.mt.dac.sysmarket.entities.Cidade;
import br.edu.ifpb.mt.dac.sysmarket.entities.Endereco;
import br.edu.ifpb.mt.dac.sysmarket.entities.Estado;
import br.edu.ifpb.mt.dac.sysmarket.entities.TipoUsuario;
import br.edu.ifpb.mt.dac.sysmarket.entities.Usuario;
import br.edu.ifpb.mt.dac.sysmarket.service.CidadeService;
import br.edu.ifpb.mt.dac.sysmarket.service.EstadoService;
import br.edu.ifpb.mt.dac.sysmarket.service.UsuarioService;
import br.edu.ifpb.mt.dac.sysmarket.util.PasswordCript;

@Named
@ViewScoped
public class EditUsuarioBean extends AbstractManagedBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6867641668781322788L;

	private Usuario usuario;

	private List<Estado> estados;

	private List<Cidade> cidades;

	@Inject
	private UsuarioService service;

	@Inject
	private EstadoService estadoService;

	@Inject
	private CidadeService cidadeService;

	@PostConstruct
	public void init() {
		estados = estadoService.getAll();
		cidades = cidadeService.getAll();
	}

	public void preRenderView() {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setEndereco(new Endereco());
		}
	}

	public String saveUsuario() {
		try {
			if (isEdicaoUsuario()) {
				service.update(usuario);
				showFlashMessageInfo("Usuário atualizado com sucesso!");
			} else {
				usuario.setSenha(PasswordCript.criptografarSenha(usuario));
				service.save(usuario);
				showFlashMessageInfo("Usuário salvo com sucesso!");
			}
		} catch (Exception e) {
			showFlashMessageError("Falha ao salvar usuário!");
		}
		return "index_usuarios?faces-redirect=true";
	}
	
	public TipoUsuario[] getTipos() {
		return TipoUsuario.values();
	}

	public boolean isEdicaoUsuario() {
		return usuario.getId() != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
}
