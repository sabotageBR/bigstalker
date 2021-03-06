package com.bigstalker.session;

import java.util.Date;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramChallenge;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.picketlink.Identity;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.basic.User;

import com.bigstalker.entity.Usuario;
import com.bigstalker.service.instagram.InstagramService;
import com.bigstalker.service.usuario.UsuarioService;
import com.bigstalker.util.UtilData;

@Named
@PicketLink
public class SimpleAuthenticator extends BaseAuthenticator {

	private @Inject DefaultLoginCredentials credentials;
	private @Inject Identity identity;
	private @Inject UsuarioService usuarioService;
	private @Inject CustomIdentity customIdentity;
	private @Inject InstagramService instagramService; 
	
	@Override
	public void authenticate() {
		System.out.println(credentials.getUserId());
		System.out.println(credentials.getPassword());
		try {
			Instagram4j instagram = null;
			if(!customIdentity.isChallenge()) {
				instagram = Instagram4j.builder().username(credentials.getUserId()).password(credentials.getPassword()).build();
				instagram.setup();
			}else {
				instagram = customIdentity.getInstagram();
			}
			InstagramLoginResult retorno =  instagram.login();
			if(retorno.getChallenge() != null && retorno.getChallenge().getUrl() != null) {
				customIdentity.setUrlChallenge(retorno.getChallenge().getUrl());
				customIdentity.setChallenge(true);
				customIdentity.setInstagram(instagram);
				
			}
			if(!retorno.getStatus().equals("fail")) {
				Usuario usuario = usuarioService.recuperarPorUsuario(retorno.getLogged_in_user().getUsername());
				if(usuario == null) {
					System.out.println("*** Obaa usuario novo: "+retorno.getLogged_in_user().getUsername());
					Usuario usuarioNew = new Usuario();
					usuarioNew.setImagem(retorno.getLogged_in_user().getProfile_pic_url());
					usuarioNew.setUsuario(retorno.getLogged_in_user().getUsername());
					usuarioNew.setNome(retorno.getLogged_in_user().getFull_name());
					usuarioNew.setSenha(credentials.getPassword());
					usuarioNew.setDataUltimoLogin(new Date());
					usuarioService.incluir(usuarioNew);
					instagramService.syncInstagram(instagram,credentials.getUserId(),usuarioNew);
					customIdentity.setUsuario(usuarioNew);
				}else {
					System.out.println("*** Melhor ainda usuario retornou: "+retorno.getLogged_in_user().getUsername());
					usuario.setImagem(retorno.getLogged_in_user().getProfile_pic_url());
					usuario.setUsuario(retorno.getLogged_in_user().getUsername());
					usuario.setNome(retorno.getLogged_in_user().getFull_name());
					usuario.setSenha(credentials.getPassword());
					UtilData utilData = new UtilData();
					if(utilData.getDiferencaDias(new Date(), usuario.getDataUltimoLogin()) > 0) {
						instagramService.syncInstagram(instagram,credentials.getUserId(),usuario);
						usuario.setDataUltimoLogin(new Date());
					}	
					usuarioService.alterar(usuario);
					customIdentity.setUsuario(usuario);
				}
				User user = new User(credentials.getUserId());
				setAccount(user);
				setStatus(AuthenticationStatus.SUCCESS);
				customIdentity.setContadorPublico(0);
				customIdentity.setInstagram(instagram);
				customIdentity.setErroLogin(false);
			}else {
				customIdentity.setErroLogin(true);
				setStatus(AuthenticationStatus.FAILURE);
				
			}	
		}catch(Exception e) {
			setStatus(AuthenticationStatus.FAILURE);
		}	
		
		
	}

	public String logout() {
		identity.logout();
		return "/login?faces-redirect=true";
	}
}