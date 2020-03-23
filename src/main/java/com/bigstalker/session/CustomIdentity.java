package com.bigstalker.session;

import java.io.Serializable;

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
	
}
