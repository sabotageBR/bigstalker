package com.bigstalker.entity;

import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name = "bst_perfil", schema = "public")
public class Perfil {
	
	@Id
	@SequenceGenerator(name="PERFIL_ID_GENERATOR", sequenceName="bst_perfil_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERFIL_ID_GENERATOR")
	private Integer id;
	
	private boolean privado;
	private String nome;
	private String imagem;
	private String usuario;
	private String biografia;
	private Integer click;
	
	@Column(name="nota_media")
	private Double notaMedia;
	
	@Column(name="nota_total")
	private Double notaTotal;
	
	@Column(name="qtd_voto")
	private Integer qtdVoto;
	
	@OneToMany(mappedBy = "perfil")
	private List<Publicacao> publicacoes;
	
	@ManyToOne
	@JoinColumn(name="id_usuario_liberacao")
	private Usuario usuarioLiberacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPrivado() {
		return privado;
	}

	public void setPrivado(boolean privado) {
		this.privado = privado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}

	public Double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(Double notaMedia) {
		this.notaMedia = notaMedia;
	}

	public Double getNotaTotal() {
		return notaTotal;
	}

	public void setNotaTotal(Double notaTotal) {
		this.notaTotal = notaTotal;
	}

	public Integer getQtdVoto() {
		return qtdVoto;
	}

	public void setQtdVoto(Integer qtdVoto) {
		this.qtdVoto = qtdVoto;
	}
	public List<Integer> getListaEstrela(){
		List<Integer> listaRetorno = new ArrayList<Integer>();
		for(int i=0;i<=getNotaMedia().intValue();i++) {
			listaRetorno.add(i);
		}
		return listaRetorno;
		
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public Usuario getUsuarioLiberacao() {
		return usuarioLiberacao;
	}

	public void setUsuarioLiberacao(Usuario usuarioLiberacao) {
		this.usuarioLiberacao = usuarioLiberacao;
	}
	 
}
