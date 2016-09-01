package br.edu.ifpb.mt.dac.sysmarket.service;

import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.mt.dac.sysmarket.dao.UsuarioDAO;
import br.edu.ifpb.mt.dac.sysmarket.entities.Usuario;
import br.edu.ifpb.mt.dac.sysmarket.util.TransacionalCdi;

public class UsuarioService implements IService<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8068163976718523286L;
	@Inject
	private UsuarioDAO dao;
	
	@TransacionalCdi
	public void save(Usuario t) {
		dao.save(t);
	}

	@TransacionalCdi
	public Usuario update(Usuario t) {
		return dao.update(t);
	}

	@TransacionalCdi
	public void remove(Usuario t) {
		dao.delete(t);
	}

	public Usuario getById(Long id) {
		return dao.getByID(id);
	}

	public List<Usuario> getAll() {
		return dao.getAll();
	}

}
