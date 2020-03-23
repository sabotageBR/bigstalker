package com.bigstalker.dao.publicacao;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.entity.Perfil;
import com.bigstalker.entity.Publicacao;

public class PublicacaoDAO extends AbstractDAO<Publicacao> {

	public Publicacao recuperarPorPostIdPerfil(String postid, Perfil perfil) {
		try {
			CriteriaQuery<Publicacao> criteria = getCriteriaBuilder().createQuery(Publicacao.class);
			Root<Publicacao> root = criteria.from(Publicacao.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("postid"), postid),
					getCriteriaBuilder().equal(root.get("perfil"), perfil))).getSingleResult();
		}catch(Exception e) {
			return null;
		}	
	}

}

