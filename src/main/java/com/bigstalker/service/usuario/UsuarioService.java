package com.bigstalker.service.usuario;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.dao.usuario.UsuarioDAO;
import com.bigstalker.entity.Usuario;
import com.bigstalker.service.AbstractService;

@Stateless
public class UsuarioService extends AbstractService<Usuario> {
	
	private @Inject UsuarioDAO usuarioDAO;

	@Override
	public AbstractDAO<Usuario> getDAO() {
		return usuarioDAO;
	}
	
	public Usuario recuperarPorUsuario(String usuario) {
		return usuarioDAO.recuperarPorUsuario(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void incluir(Usuario entidade) {
		// TODO Auto-generated method stub
		super.incluir(entidade);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void alterar(Usuario entidade) {
		// TODO Auto-generated method stub
		super.alterar(entidade);
	}
	
	
	

}
