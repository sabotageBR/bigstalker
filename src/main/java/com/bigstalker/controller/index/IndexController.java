package com.bigstalker.controller.index;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.bigstalker.controller.AbstractController;
import com.bigstalker.entity.Notificacao;
import com.bigstalker.service.mail.MailService;
import com.bigstalker.service.notificacao.NotificacaoService;
import com.bigstalker.service.perfil.PerfilService;
import com.bigstalker.service.publicacao.PublicacaoService;
import com.bigstalker.service.publicacaoimagem.PublicacaoImagemService;
import com.bigstalker.service.usuario.UsuarioService;
import com.bigstalker.to.IndexTO;


@Named
@RequestScoped
public class IndexController extends AbstractController<IndexTO> {

	private static final long serialVersionUID = 1536609066580603684L;
	
	private @Inject PerfilService perfilService;
	private @Inject UsuarioService usuarioService;
	private @Inject NotificacaoService notificacaoService;
	private @Inject MailService mailService;
	private @Inject PublicacaoService publicacaoService;
	private @Inject PublicacaoImagemService publicacaoImagemService;
	
	@PostConstruct
	private void init() {
		getTo().setPerfisTopClick(perfilService.listarTopClick());
		getTo().setPerfisTopNota(perfilService.listarTopNota());
		getTo().setCountPerfil(perfilService.countPerfil());
		getTo().setCountUsuario(usuarioService.countUsuario());
		getTo().setCountPublicacao(publicacaoService.countPublicacao());
		getTo().setCountImagem(publicacaoImagemService.countImagem());
	}
	
	public void enviarEmailTeste() {
		Notificacao notificacao = notificacaoService.listar().get(0);
		mailService.enviarEmail(notificacao, "EMAIL TESTE INDEX", "AQUELE TEXTO BASICO");
	}
	
	
}
