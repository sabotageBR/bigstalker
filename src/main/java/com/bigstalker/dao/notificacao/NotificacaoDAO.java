package com.bigstalker.dao.notificacao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.entity.Notificacao;

public class NotificacaoDAO extends AbstractDAO<Notificacao> {

	
	public List<Notificacao> pesquisarEmAberto(String usuario){
		CriteriaQuery<Notificacao> criteria = getCriteriaBuilder().createQuery(Notificacao.class);
		Root<Notificacao> root = criteria.from(Notificacao.class);
		return getManager().createQuery(criteria.select(root).where(
				getCriteriaBuilder().equal(root.get("alertado"), false),
				getCriteriaBuilder().equal(root.get("usuarioNaoEncontrado"), usuario)
				)).getResultList();
	}
}

