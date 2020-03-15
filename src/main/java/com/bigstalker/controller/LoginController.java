package com.bigstalker.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.jinstagram.Instagram;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;

import com.bigstalker.session.CustomIdentity;
import com.bigstalker.to.LoginTO;

@Model
public class LoginController extends AbstractController<LoginTO>{

	
	private @Inject CustomIdentity customIdentity;
	private @Inject com.bigstalker.service.instagram.InstagramService instagramService;
	
	@PostConstruct
	public void init() {
		String retorno;
		try {
			if(getRequest().getParameter("code") != null) {
				Verifier verifier = new Verifier(getRequest().getParameter("code"));
				if(customIdentity.getService() != null){
			        Token accessToken = customIdentity.getService().getAccessToken(verifier);
			        Instagram instagram = new Instagram(accessToken);
			        customIdentity.setInstagram(instagram);
			        instagramService.syncInstagram(customIdentity.getService(), customIdentity.getInstagram());
			       // identity.login();
			        getTo().setToken(getRequest().getParameter("code"));
			        retorno =  "/home?faces-redirect=true";
		        }else{
		        	retorno =  null;
		        }
			}else {
				initProperties();
			}
		}catch(Exception e) {
		}	
		
	}
	
	private void initProperties(){
	    InstagramService service = new InstagramAuthService()
	            .apiKey("f637a7fbbe0643f4aa84ccf94eb0c54e")
	            .apiSecret("0e69942400924960b183500dee00f248")
	            .callback("http://bigstalker.com/login")
	            .scope("basic")
	            .build();
	    customIdentity.setService(service);
	    getTo().setUrlInstagram(service.getAuthorizationUrl());
	}
	
	
	 

}