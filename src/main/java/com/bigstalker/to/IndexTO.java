package com.bigstalker.to;

import java.util.ArrayList;
import java.util.List;

import com.bigstalker.entity.Perfil;

public class IndexTO {
	
	private List<Perfil> perfisTopNota;
	private List<Perfil> perfisTopClick;
	private Integer countPerfil;
	private Integer countUsuario;
	private Integer countPublicacao;
	private Integer countImagem;
	
	public List<Perfil> getPerfisTopNota() {
		if (perfisTopNota == null) {
			perfisTopNota = new ArrayList<Perfil>();
		}
		return perfisTopNota;
	}
	
	public void setPerfisTopNota(List<Perfil> perfisTopNota) {
		this.perfisTopNota = perfisTopNota;
	}
	
	public List<Perfil> getPerfisTopClick() {
		if (perfisTopClick == null) {
			perfisTopClick = new ArrayList<Perfil>();
		}
		return perfisTopClick;
	}
	
	public void setPerfisTopClick(List<Perfil> perfisTopClick) {
		this.perfisTopClick = perfisTopClick;
	}

	public Integer getCountPerfil() {
		return countPerfil;
	}

	public void setCountPerfil(Integer countPerfil) {
		this.countPerfil = countPerfil;
	}

	public Integer getCountUsuario() {
		return countUsuario;
	}

	public void setCountUsuario(Integer countUsuario) {
		this.countUsuario = countUsuario;
	}

	public Integer getCountPublicacao() {
		return countPublicacao;
	}

	public void setCountPublicacao(Integer countPublicacao) {
		this.countPublicacao = countPublicacao;
	}

	public Integer getCountImagem() {
		return countImagem;
	}

	public void setCountImagem(Integer countImagem) {
		this.countImagem = countImagem;
	}
	
}
