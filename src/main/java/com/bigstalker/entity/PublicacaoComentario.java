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
@Table(name = "bst_perfil_publicacao_comentario", schema = "public")
public class PublicacaoComentario {
	
	@Id
	@SequenceGenerator(name="PERFIL_PUBLICACAO_COMENTARIO_ID_GENERATOR", sequenceName="bst_perfil_publicacao_comentario_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_PUBLICACAO_COMENTARIO_ID_GENERATOR")
	private Integer id;
	
	private String texto;
	
	@ManyToOne
	@JoinColumn(name="id_perfil_publicacao")
	private Publicacao publicacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
		
}
