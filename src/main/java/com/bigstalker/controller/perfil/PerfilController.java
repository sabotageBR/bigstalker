package com.bigstalker.controller.perfil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import com.bigstalker.controller.AbstractController;
import com.bigstalker.service.instagram.InstagramService;
import com.bigstalker.service.notificacao.NotificacaoService;
import com.bigstalker.service.perfil.PerfilService;
import com.bigstalker.session.CustomIdentity;
import com.bigstalker.to.PerfilTO;


@Named
@RequestScoped
public class PerfilController extends AbstractController<PerfilTO> {

	
	private @Inject PerfilService perfilService;
	private @Inject CustomIdentity customIdentity;
	private @Inject InstagramService instagramService;
	
	private @Inject NotificacaoService notificacaoService;
	
	@PostConstruct
	private void init() {
		if(getRequest().getParameter("usuario") != null) {
			if(customIdentity.getUsuario() == null) {
				customIdentity.setContadorPublico(customIdentity.getContadorPublico() + 1);
			}
			getTo().setPerfil(perfilService.recuperarCompletoPorUsuario(getRequest().getParameter("usuario")));
			getTo().getPerfil().setClick(getTo().getPerfil().getClick() + 1);
			perfilService.alterar(getTo().getPerfil());
			getTo().setAlertaCriado(false);
		}	
	}
	
	
	public void pesquisar() {
		getTo().setAlertaCriado(false);
		if(getTo().getPerfilPesquisa().getNome() != null && !getTo().getPerfilPesquisa().getNome().equals("") && getTo().getPerfilPesquisa().getNome().length() > 1) {
			getTo().setPerfis(perfilService.pesquisarPorTermo(getTo().getPerfilPesquisa().getNome()));
		}	
	}
	
	public void encontrarEntreAmigos() {
		List<InstagramUserSummary> retorno = instagramService.encontrarEntreAmigos(customIdentity.getInstagram(), getTo().getPerfilPesquisa().getNome(), customIdentity.getUsuario().getUsuario());
		System.out.println(retorno);
	}
	
	public void criarAlerta() {
		getTo().getNotificacao().setAlertado(false);
		getTo().getNotificacao().setDataCriacao(new Date());
		getTo().getNotificacao().setUsuarioNaoEncontrado(getTo().getPerfilPesquisa().getNome());
		notificacaoService.incluir(getTo().getNotificacao());
		getTo().setPerfilPesquisa(null);
		getTo().setPerfis(null);
		getTo().setAlertaCriado(true);
	}
	
	public List<Integer> getListaEstrelaMediaPerfil(){
		List<Integer> retorno = new ArrayList<Integer>();
		for(int i=0;i<=getTo().getPerfil().getNotaMedia().intValue();i++) {
			retorno.add(i);
		}
		return retorno;
	}
	
}
