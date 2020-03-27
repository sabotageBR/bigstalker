package com.bigstalker.dao.publicacaoimagem;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.entity.PublicacaoImagem;

public class PublicacaoImagemDAO extends AbstractDAO<PublicacaoImagem> {

	public Integer countImagem() {
		CriteriaQuery<Long> criteria = getCriteriaBuilder().createQuery(Long.class);
		Root<PublicacaoImagem> root = criteria.from(PublicacaoImagem.class);
		criteria.select(getCriteriaBuilder().count(root));
		return getManager().createQuery(criteria).getSingleResult().intValue();
	}
}

