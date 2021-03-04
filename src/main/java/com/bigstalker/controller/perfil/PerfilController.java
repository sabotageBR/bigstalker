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

	
	private static final long serialVersionUID = 8799666236109984333L;
	
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
			System.out.println("********* Visualizou o perfil: "+getRequest().getParameter("usuario"));
		}	
	}
	
	
	public void pesquisar() {
		
		getTo().setAlertaCriado(false);
		if(getTo().getPerfilPesquisa().getNome() != null && !getTo().getPerfilPesquisa().getNome().equals("") && getTo().getPerfilPesquisa().getNome().length() > 1) {
			getTo().setPerfis(perfilService.pesquisarPorTermo(getTo().getPerfilPesquisa().getNome()));
		}
		System.out.println("Pesquisou pelo termo:"+getTo().getPerfilPesquisa().getNome());
		System.out.println("Tamanho:"+getTo().getPerfis().size());
		System.out.println("**********************************");
	}
	
	public void encontrarEntreAmigos() {
		List<InstagramUserSummary> retorno = instagramService.encontrarEntreAmigos(customIdentity.getInstagram(), getTo().getPerfilPesquisa().getNome(), customIdentity.getUsuario().getUsuario());
		System.out.println(retorno);
	}
	
	public void criarAlerta() {
		if(getTo().getNotificacao().getEmailAlerta() != null && !getTo().getNotificacao().getEmailAlerta().equals("")) {
			getTo().getNotificacao().setAlertado(false);
			getTo().getNotificacao().setDataCriacao(new Date());
			getTo().getNotificacao().setUsuarioNaoEncontrado(getTo().getPerfilPesquisa().getNome());
			notificacaoService.incluir(getTo().getNotificacao());
			getTo().setPerfilPesquisa(null);
			getTo().setPerfis(null);
			getTo().setAlertaCriado(true);
			getTo().setNotificacao(null);
		}else {
			String mensagem = getMessage("label.global.emailobrigatorio");
			getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem));
		}	
	}
	
	public List<Integer> getListaEstrelaMediaPerfil(){
		List<Integer> retorno = new ArrayList<Integer>();
		for(int i=1;i<=getTo().getPerfil().getNotaMedia().intValue();i++) {
			retorno.add(i);
		}
		return retorno;
	}
	
	public void votar(Integer voto) {
		getTo().getPerfil().setQtdVoto(getTo().getPerfil().getQtdVoto() + 1);
		getTo().getPerfil().setNotaTotal(getTo().getPerfil().getNotaTotal() + voto);
		getTo().getPerfil().setNotaMedia(getTo().getPerfil().getNotaTotal() / getTo().getPerfil().getQtdVoto());
		perfilService.alterar(getTo().getPerfil());
		customIdentity.getMapaVoto().put(getTo().getPerfil().getUsuario(),voto);
	}
	
	public boolean isVotou() {
		return customIdentity.getMapaVoto().get(getTo().getPerfil().getUsuario()) != null;
	}
	
}
