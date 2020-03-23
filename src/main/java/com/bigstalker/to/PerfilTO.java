package com.bigstalker.to;

import java.util.ArrayList;
import java.util.List;

import com.bigstalker.entity.Perfil;

public class PerfilTO {

	private Perfil perfil;
	private Perfil perfilPesquisa;	
	private List<Perfil> perfis;
	private Integer voto;

	public Perfil getPerfil() {
		if (perfil == null) {
			perfil = new Perfil();
		}

		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfis() {
		if (perfis == null) {
			perfis = new ArrayList<Perfil>();
		}
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Perfil getPerfilPesquisa() {
		if (perfilPesquisa == null) {
			perfilPesquisa = new Perfil();
		}
		return perfilPesquisa;
	}

	public void setPerfilPesquisa(Perfil perfilPesquisa) {
		this.perfilPesquisa = perfilPesquisa;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}
	
	
}
