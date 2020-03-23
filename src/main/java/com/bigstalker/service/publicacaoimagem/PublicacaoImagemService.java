package com.bigstalker.service.publicacaoimagem;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.dao.publicacaoimagem.PublicacaoImagemDAO;
import com.bigstalker.entity.PublicacaoImagem;
import com.bigstalker.service.AbstractService;

@Stateless
public class PublicacaoImagemService extends AbstractService<PublicacaoImagem> {
	
	private @Inject PublicacaoImagemDAO publicacaoImagemDAO;

	@Override
	public AbstractDAO<PublicacaoImagem> getDAO() {
		return publicacaoImagemDAO;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void incluir(PublicacaoImagem entidade) {
		super.incluir(entidade);
	}

}
