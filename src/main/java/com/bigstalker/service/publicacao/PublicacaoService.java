package com.bigstalker.service.publicacao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.dao.publicacao.PublicacaoDAO;
import com.bigstalker.entity.Perfil;
import com.bigstalker.entity.Publicacao;
import com.bigstalker.service.AbstractService;

@Stateless
public class PublicacaoService extends AbstractService<Publicacao> {
	
	private @Inject PublicacaoDAO publicacaoDAO;

	@Override
	public AbstractDAO<Publicacao> getDAO() {
		return publicacaoDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Publicacao recuperarPorPostIdPerfil(String postid,Perfil perfil) {
		return publicacaoDAO.recuperarPorPostIdPerfil(postid,perfil);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void incluir(Publicacao entidade) {
		try {
			super.incluir(entidade);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public Integer countPublicacao() {
		return publicacaoDAO.countPublicacao();
	}

}
