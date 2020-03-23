package com.bigstalker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bst_perfil_publicacao_like", schema = "public")
public class PublicacaoLike {
	
	@Id
	@SequenceGenerator(name="PERFIL_PUBLICACAO_LIKE_ID_GENERATOR", sequenceName="bst_perfil_publicacao_like_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_PUBLICACAO_LIKE_ID_GENERATOR")
	private Integer id;
	
	private String usuario;
	
	@ManyToOne
	@JoinColumn(name="id_perfil_publicacao")
	private Publicacao publicacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
}
