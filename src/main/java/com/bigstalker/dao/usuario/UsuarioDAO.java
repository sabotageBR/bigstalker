package com.bigstalker.dao.usuario;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.entity.Perfil;
import com.bigstalker.entity.Usuario;

@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {

	public Usuario recuperarPorUsuario(String usuario) {
		try {
			CriteriaQuery<Usuario> criteria = getCriteriaBuilder().createQuery(Usuario.class);
			Root<Usuario> root = criteria.from(Usuario.class);
			return getManager().createQuery(
					criteria.select(root).where(
							getCriteriaBuilder().equal(root.get("usuario"), usuario))).getSingleResult();
		}catch(Exception e) {
			return null;
		}	
	}
	
	public Integer countUsuario() {
		CriteriaQuery<Long> criteria = getCriteriaBuilder().createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(getCriteriaBuilder().count(root));
		return getManager().createQuery(criteria).getSingleResult().intValue();
	}

}

