package com.bigstalker.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.brunocvcunha.instagram4j.Instagram4j;

import com.bigstalker.entity.Usuario;

@Named
@SessionScoped
public class CustomIdentity implements Serializable {

	private static final long serialVersionUID = 8606369819408324025L;

	private Usuario usuario;
	private Instagram4j instagram;
	private Integer contadorPublico = 0;
	private Map<String, Integer> mapaVoto;
	private String urlChallenge;
	private boolean erroLogin;

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Instagram4j getInstagram() {
		return instagram;
	}
	public void setInstagram(Instagram4j instagram) {
		this.instagram = instagram;
	}
	public Integer getContadorPublico() {
		return contadorPublico;
	}
	public void setContadorPublico(Integer contadorPublico) {
		this.contadorPublico = contadorPublico;
	}
	public Map<String, Integer> getMapaVoto() {
		if (mapaVoto == null) {
			mapaVoto = new HashMap<String, Integer>();
		}
		return mapaVoto;
	}
	public void setMapaVoto(Map<String, Integer> mapaVoto) {
		this.mapaVoto = mapaVoto;
	}
	public String getUrlChallenge() {
		return urlChallenge;
	}
	public void setUrlChallenge(String urlChallenge) {
		this.urlChallenge = urlChallenge;
	}
	public boolean isErroLogin() {
		return erroLogin;
	}
	public void setErroLogin(boolean erroLogin) {
		this.erroLogin = erroLogin;
	}
	
}
