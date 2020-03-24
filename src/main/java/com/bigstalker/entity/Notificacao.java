package com.bigstalker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bst_notificacao", schema = "public")
public class Notificacao {
	
	@Id
	@SequenceGenerator(name="NOTIFICACAO_ID_GENERATOR", sequenceName="bst_notificacao_id_seq", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTIFICACAO_ID_GENERATOR")
	private Integer id;
	
	@Column(name = "usuario_nao_encontrado")
	private String usuarioNaoEncontrado;
	
	@Column(name = "email_alerta")
	private String emailAlerta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	private boolean alertado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuarioNaoEncontrado() {
		return usuarioNaoEncontrado;
	}

	public void setUsuarioNaoEncontrado(String usuarioNaoEncontrado) {
		this.usuarioNaoEncontrado = usuarioNaoEncontrado;
	}

	public String getEmailAlerta() {
		return emailAlerta;
	}

	public void setEmailAlerta(String emailAlerta) {
		this.emailAlerta = emailAlerta;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public boolean isAlertado() {
		return alertado;
	}

	public void setAlertado(boolean alertado) {
		this.alertado = alertado;
	}
	
}
