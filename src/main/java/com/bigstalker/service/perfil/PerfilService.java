package com.bigstalker.service.perfil;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.dao.perfil.PerfilDAO;
import com.bigstalker.entity.Perfil;
import com.bigstalker.service.AbstractService;

@Stateless
public class PerfilService extends AbstractService<Perfil> {
	
	private @Inject PerfilDAO perfilDAO;

	@Override
	public AbstractDAO<Perfil> getDAO() {
		return perfilDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Perfil recuperarPorUsuario(String usuario) {
		return perfilDAO.recuperarPorUsuario(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void incluir(Perfil entidade) {
		super.incluir(entidade);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void alterar(Perfil entidade) {
		super.alterar(entidade);
	}
	
	public List<Perfil> listarTopClick() {
		return perfilDAO.listarTopClick();
	}
	
	public List<Perfil> listarTopNota() {
		return perfilDAO.listarTopNota();
	}

	public Perfil recuperarCompletoPorUsuario(String usuario) {
		return perfilDAO.recuperarCompletoPorUsuario(usuario);
	}

	public List<Perfil> pesquisarPorTermo(String termo) {
		return perfilDAO.pesquisarPorTermo(termo);
	}
	

}
