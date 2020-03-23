package com.bigstalker.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bst_perfil_publicacao", schema = "public")
public class Publicacao {
	
	@Id
	@SequenceGenerator(name="PERFIL_PUBLICACAO_ID_GENERATOR", sequenceName="bst_perfil_publicacao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_PUBLICACAO_ID_GENERATOR")
	private Integer id;

	private String postid;
	
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_add")
	private Date dataAdd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_publicacao")
	private Date dataPublicacao;
	
	@OneToMany(mappedBy = "publicacao")
	private List<PublicacaoImagem> imagens;
	
	@OneToMany(mappedBy = "publicacao")
	private List<PublicacaoComentario> comentarios;
	
	@OneToMany(mappedBy = "publicacao")
	private List<PublicacaoLike> likes;
	
	@ManyToOne
	@JoinColumn(name="id_perfil")
	private Perfil perfil;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PublicacaoImagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<PublicacaoImagem> imagens) {
		this.imagens = imagens;
	}

	public List<PublicacaoComentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<PublicacaoComentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<PublicacaoLike> getLikes() {
		return likes;
	}

	public void setLikes(List<PublicacaoLike> likes) {
		this.likes = likes;
	}

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataAdd() {
		return dataAdd;
	}

	public void setDataAdd(Date dataAdd) {
		this.dataAdd = dataAdd;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
}
