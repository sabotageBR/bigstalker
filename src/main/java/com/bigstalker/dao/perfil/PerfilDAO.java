package com.bigstalker.dao.perfil;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.entity.Perfil;
import com.bigstalker.entity.Publicacao;

public class PerfilDAO extends AbstractDAO<Perfil> {

	public Perfil recuperarPorUsuario(String usuario) {
		try {
			CriteriaQuery<Perfil> criteria = getCriteriaBuilder().createQuery(Perfil.class);
			Root<Perfil> root = criteria.from(Perfil.class);
			return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("usuario"), usuario))).getSingleResult();
		}catch (Exception e) {
			return null;
		}	
	}

	public List<Perfil> listarTopClick() {
		CriteriaQuery<Perfil> criteria = getCriteriaBuilder().createQuery(Perfil.class);
		Root<Perfil> root = criteria.from(Perfil.class);
		return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("privado"), true))
				.orderBy(getCriteriaBuilder().desc(root.get("click")))).setMaxResults(25).getResultList();
	}
	
	public List<Perfil> listarTopNota() {
		CriteriaQuery<Perfil> criteria = getCriteriaBuilder().createQuery(Perfil.class);
		Root<Perfil> root = criteria.from(Perfil.class);
		return getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("privado"), true))
						.orderBy(getCriteriaBuilder().desc(root.get("notaMedia")))).setMaxResults(25).getResultList();
	}

	public Perfil recuperarCompletoPorUsuario(String usuario) {
		try {
			CriteriaQuery<Perfil> criteria = getCriteriaBuilder().createQuery(Perfil.class);
			Root<Perfil> root = criteria.from(Perfil.class);
			return inicializar(getManager().createQuery(criteria.select(root).where(getCriteriaBuilder().equal(root.get("usuario"), usuario))).getSingleResult());
		}catch (Exception e) {
			return null;
		}
	}
	
	private Perfil inicializar(Perfil perfil) {
		if(perfil.getPublicacoes() != null) {
			for(Publicacao publicacao:perfil.getPublicacoes()) {
				if(publicacao.getComentarios() != null) {
					publicacao.getComentarios().size();
				}
				
				if(publicacao.getImagens() != null) {
					publicacao.getImagens().size();
				}
				
				if(publicacao.getLikes() != null) {
					publicacao.getLikes().size();
				}
			}
		}
		return perfil;
		
	}

	public List<Perfil> pesquisarPorTermo(String termo) {
			CriteriaQuery<Perfil> criteria = getCriteriaBuilder().createQuery(Perfil.class);
			Root<Perfil> root = criteria.from(Perfil.class);
			return getManager().createQuery(criteria.select(root)
					.where(
							getCriteriaBuilder().or(
									getCriteriaBuilder().like(getCriteriaBuilder().lower(root.<String>get("nome")), "%"+termo.toLowerCase()+"%"),
									getCriteriaBuilder().like(getCriteriaBuilder().lower(root.<String>get("usuario")), "%"+termo.toLowerCase()+"%"))
							)
					.orderBy(getCriteriaBuilder().desc(root.get("click")))).setMaxResults(20).getResultList();
	}
	
	public Integer countPerfil() {
		CriteriaQuery<Long> criteria = getCriteriaBuilder().createQuery(Long.class);
		Root<Perfil> root = criteria.from(Perfil.class);
		criteria.select(getCriteriaBuilder().count(root));
		return getManager().createQuery(criteria).getSingleResult().intValue();
	}

}

