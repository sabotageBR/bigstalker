package com.bigstalker.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import org.jinstagram.Instagram;
import org.jinstagram.auth.oauth.InstagramService;

@SessionScoped
public class CustomIdentity implements Serializable {

	private static final long serialVersionUID = 8606369819408324025L;
	
	private String code;
	private InstagramService service;
	private Instagram instagram;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public InstagramService getService() {
		return service;
	}

	public void setService(InstagramService service) {
		this.service = service;
	}

	public Instagram getInstagram() {
		return instagram;
	}

	public void setInstagram(Instagram instagram) {
		this.instagram = instagram;
	}
	
	
}
