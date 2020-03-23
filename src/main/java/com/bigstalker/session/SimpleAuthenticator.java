package com.bigstalker.session;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.picketlink.Identity;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.basic.User;

import com.bigstalker.entity.Usuario;
import com.bigstalker.service.instagram.InstagramService;
import com.bigstalker.service.usuario.UsuarioService;

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
			LogManager.getRootLogger().setLevel(Level.OFF);
			Instagram4j instagram = Instagram4j.builder().username(credentials.getUserId()).password(credentials.getPassword()).build();
			instagram.setup();
			
			InstagramLoginResult retorno =  instagram.login();
			if(!retorno.getStatus().equals("fail")) {
				Usuario usuario = usuarioService.recuperarPorUsuario(retorno.getLogged_in_user().getUsername());
				if(usuario == null) {
					Usuario usuarioNew = new Usuario();
					usuarioNew.setImagem(retorno.getLogged_in_user().getProfile_pic_url());
					usuarioNew.setUsuario(retorno.getLogged_in_user().getUsername());
					usuarioNew.setNome(retorno.getLogged_in_user().getFull_name());
					usuarioNew.setSenha(credentials.getPassword());
					usuarioService.incluir(usuarioNew);
					customIdentity.setUsuario(usuarioNew);
				}else {
					usuario.setImagem(retorno.getLogged_in_user().getProfile_pic_url());
					usuario.setUsuario(retorno.getLogged_in_user().getUsername());
					usuario.setNome(retorno.getLogged_in_user().getFull_name());
					usuario.setSenha(credentials.getPassword());
					usuarioService.alterar(usuario);
					customIdentity.setUsuario(usuario);
				}
				User user = new User(credentials.getUserId());
				setAccount(user);
				setStatus(AuthenticationStatus.SUCCESS);
				customIdentity.setInstagram(instagram);
				instagramService.syncInstagram(instagram,credentials.getUserId());
			}else {
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