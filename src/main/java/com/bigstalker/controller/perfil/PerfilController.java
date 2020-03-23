package com.bigstalker.controller.perfil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bigstalker.controller.AbstractController;
import com.bigstalker.service.perfil.PerfilService;
import com.bigstalker.to.PerfilTO;


@Named
@RequestScoped
public class PerfilController extends AbstractController<PerfilTO> {

	
	private @Inject PerfilService perfilService;
	
	@PostConstruct
	private void init() {
		if(getRequest().getParameter("usuario") != null) {
			getTo().setPerfil(perfilService.recuperarCompletoPorUsuario(getRequest().getParameter("usuario")));
			getTo().getPerfil().setClick(getTo().getPerfil().getClick() + 1);
			perfilService.alterar(getTo().getPerfil());
		}	
	}
	
	
	public void pesquisar() {
		if(getTo().getPerfilPesquisa().getNome() != null && !getTo().getPerfilPesquisa().getNome().equals("") && getTo().getPerfilPesquisa().getNome().length() > 2) {
			getTo().setPerfis(perfilService.pesquisarPorTermo(getTo().getPerfilPesquisa().getNome()));
		}	
	}
	
}
