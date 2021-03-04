package com.bigstalker.service.instagram;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.ImageMeta;
import org.brunocvcunha.instagram4j.requests.payload.InstagramCarouselMediaItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import com.bigstalker.entity.Perfil;
import com.bigstalker.entity.Publicacao;
import com.bigstalker.entity.PublicacaoImagem;
import com.bigstalker.entity.Usuario;
import com.bigstalker.service.perfil.PerfilService;
import com.bigstalker.service.publicacao.PublicacaoService;
import com.bigstalker.service.publicacaoimagem.PublicacaoImagemService;

@Stateless
public class InstagramService {

	private @Inject PerfilService perfilService;
	private @Inject PublicacaoService publicacaoService;
	private @Inject PublicacaoImagemService publicacaoImagemService;
	
	
	@Asynchronous
	public void syncInstagram(Instagram4j instagram, String usuario, Usuario usuarioLiberacao)throws Exception{
		try{
			InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(usuario));
			System.out.println("ID for @github is " + userResult.getUser().getPk());
			System.out.println("Number of following: " + userResult.getUser().getFollowing_count());
			InstagramGetUserFollowersResult githubFollowing = instagram.sendRequest(new InstagramGetUserFollowingRequest(userResult.getUser().getPk()));
			List<InstagramUserSummary> users2 = githubFollowing.getUsers();
			int i = 0;
			InstagramSearchUsernameResult userResultFriend = null;
			InstagramFeedResult feedFriend = null;
			if(users2 != null) {
				for (InstagramUserSummary user : users2) {
					i++;
					System.out.println("***********************************************");
				    System.out.println(i+" User " + user.getUsername()+" Privado:"+user.is_private());
				    try {
					    userResultFriend = instagram.sendRequest(new InstagramSearchUsernameRequest(user.getUsername()));
					    Perfil perfil = gravarPerfil(userResultFriend,usuarioLiberacao);
					    feedFriend = instagram.sendRequest(new InstagramUserFeedRequest(userResultFriend.getUser().getPk()));
					    if(feedFriend.getItems() != null && feedFriend.getItems().size() > 0) {
						    for (InstagramFeedItem feedResult : feedFriend.getItems()) {
						    	gravarPublicacao(userResultFriend, perfil, feedResult);    
						    }
					    }
					    Thread.currentThread().sleep(5000);
				    }catch (Exception e) {
				    	e.printStackTrace();
						System.out.println("Erro no login: "+e.getMessage());
					}    
				}
			}	
			
		}catch(Exception e){
			e.printStackTrace();
		}  
	}


	private void gravarPublicacao(InstagramSearchUsernameResult userResultFriend, Perfil perfil,
			InstagramFeedItem feedResult) {
		Publicacao publicacao = publicacaoService.recuperarPorPostIdPerfil(String.valueOf(feedResult.getPk()), perfil);
		if(publicacao == null) {
		    System.out.println("Privado:"+userResultFriend.getUser().is_private()+" login:"+userResultFriend.getUser().getUsername()+" Post ID: " + feedResult);
		    if(userResultFriend.getUser().getUsername().equals("melissamelmaia")) {
		    	System.out.println("break");
		    }
		    publicacao = new Publicacao();
		    publicacao.setPostid(String.valueOf(feedResult.getPk()));
		    publicacao.setPerfil(perfil);
		    if(feedResult != null && feedResult.getCaption() != null && feedResult.getCaption().getText() != null) {
		    	publicacao.setTexto(feedResult.getCaption().getText().length() < 3000?feedResult.getCaption().getText():feedResult.getCaption().getText().substring(0, 3000));
		    }	
		    publicacao.setDataAdd(new Date());
		    publicacao.setDataPublicacao(new Date(feedResult.getDevice_timestamp()));
		    publicacao.setDuracaoVideo(feedResult.getVideo_duration());
		    publicacaoService.incluir(publicacao);
		    if(feedResult.getVideo_duration() > 0) {
		    	System.out.println("PARAR");
		    }
		    if(feedResult.getVideo_duration() == 0) {
			    if(feedResult.getImage_versions2() != null) {
			    	if(feedResult.getImage_versions2().getCandidates() != null) {
			    		for(ImageMeta imagemInsta:feedResult.getImage_versions2().getCandidates()) {
			    			gravarImagem(publicacao, imagemInsta);
			    		}
			    	}
			    }
			    if(feedResult.getCarousel_media() != null) {
			    	for(InstagramCarouselMediaItem mediaItem:feedResult.getCarousel_media()) {
			    		if(mediaItem.getImage_versions2() != null && mediaItem.getImage_versions2().getCandidates() != null) {
			    			for(ImageMeta imagemInsta:mediaItem.getImage_versions2().getCandidates()) {
			    				gravarImagem(publicacao, imagemInsta);
			    			}
			    		}	
			    	}
			    }
		    }else {    
		    	if(feedResult.getVideo_versions() != null) {
		    		for(ImageMeta imagemInsta:feedResult.getVideo_versions()) {
		    			gravarImagem(publicacao, imagemInsta);
		    		}
		    	}
		    }	
		}
	}


	private void gravarImagem(Publicacao publicacao, ImageMeta imagemInsta) {
		PublicacaoImagem imagem = new PublicacaoImagem();
		imagem.setPublicacao(publicacao);
		imagem.setUrl(imagemInsta.getUrl());
		publicacaoImagemService.incluir(imagem);
	}


	private Perfil gravarPerfil(InstagramSearchUsernameResult userResultFriend,Usuario usuarioLiberacao) {
		Perfil perfil = perfilService.recuperarPorUsuario(userResultFriend.getUser().getUsername());
		if(perfil == null) {
			perfil = new Perfil();
			perfil.setNome(userResultFriend.getUser().getFull_name());
			perfil.setPrivado(userResultFriend.getUser().is_private());
			perfil.setUsuario(userResultFriend.getUser().getUsername());
			perfil.setImagem(userResultFriend.getUser().getProfile_pic_url());
			perfil.setBiografia(userResultFriend.getUser().getBiography());
			perfil.setClick(0);
			perfil.setNotaMedia(Double.valueOf(0));
			perfil.setNotaTotal(Double.valueOf(0));			
			perfil.setQtdVoto(0);
			perfil.setUsuarioLiberacao(usuarioLiberacao);
			perfilService.incluir(perfil);
		}else {
			perfil.setNome(userResultFriend.getUser().getFull_name());
			perfil.setPrivado(userResultFriend.getUser().is_private());
			perfil.setUsuario(userResultFriend.getUser().getUsername());
			perfil.setImagem(userResultFriend.getUser().getProfile_pic_url());
			perfil.setBiografia(userResultFriend.getUser().getBiography());
			perfilService.alterar(perfil);
		}
		return perfil;
	}
	
	public List<InstagramUserSummary> encontrarEntreAmigos(Instagram4j instagram, String usuario, String usuarioLogado) {
		List<InstagramUserSummary> perfisEncontrados = new ArrayList<InstagramUserSummary>();
		try {
			InstagramSearchUsernameResult usuarioLogadoInsta = instagram.sendRequest(new InstagramSearchUsernameRequest(usuarioLogado));
			InstagramGetUserFollowersResult amigosUsuarioLogadoInsta = instagram.sendRequest(new InstagramGetUserFollowingRequest(usuarioLogadoInsta.getUser().getPk()));
			
			List<InstagramUserSummary> amigos = amigosUsuarioLogadoInsta.getUsers();
			if(amigos != null) {
				for (InstagramUserSummary amigo : amigos) {
					InstagramGetUserFollowersResult amigosDosAmigos = instagram.sendRequest(new InstagramGetUserFollowingRequest(amigo.getPk()));
					
					if(amigosDosAmigos != null && amigosDosAmigos.getUsers() != null) {
						for (InstagramUserSummary amigoDoAmigo : amigosDosAmigos.getUsers()) {
							System.out.println("Amigo :"+amigo.getUsername()+" Amigo do Amigo:"+amigoDoAmigo.getUsername());
							if(amigoDoAmigo.getUsername().equals(usuario)) {
								perfisEncontrados.add(amigo);
							}
						}
					}	
				}
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfisEncontrados;
		
		
	}
}
