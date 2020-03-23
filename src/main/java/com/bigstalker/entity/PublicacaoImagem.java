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
@Table(name = "bst_perfil_publicacao_imagem", schema = "public")
public class PublicacaoImagem {
	
	@Id
	@SequenceGenerator(name="PERFIL_PUBLICACAO_IMAGEM_ID_GENERATOR", sequenceName="bst_perfil_publicacao_imagem_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_PUBLICACAO_IMAGEM_ID_GENERATOR")
	private Integer id;
	
	private String url;
	
	@ManyToOne
	@JoinColumn(name="id_perfil_publicacao")
	private Publicacao publicacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
		
}
