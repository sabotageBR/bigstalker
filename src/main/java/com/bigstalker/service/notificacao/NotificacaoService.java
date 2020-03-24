package com.bigstalker.service.notificacao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bigstalker.dao.AbstractDAO;
import com.bigstalker.dao.notificacao.NotificacaoDAO;
import com.bigstalker.entity.Notificacao;
import com.bigstalker.entity.Perfil;
import com.bigstalker.service.AbstractService;
import com.bigstalker.service.mail.MailService;

@Stateless
public class NotificacaoService extends AbstractService<Notificacao> {
	
	private @Inject NotificacaoDAO notificacaoDAO;
	private @Inject MailService mailService;

	@Override
	public AbstractDAO<Notificacao> getDAO() {
		return notificacaoDAO;
	}
	
	public void notificarPerfilEncontrado(Perfil perfil) {
		List<Notificacao> notificacoes= notificacaoDAO.pesquisarEmAberto(perfil.getUsuario());
		if(notificacoes != null) {
			for(Notificacao notificacao:notificacoes) {
				StringBuilder texto = new StringBuilder("Você está com sorte, o perfil "+perfil.getUsuario()+" foi desbloqueado. <br />");
				texto.append("Acesse o link <a href=\"http://www.bigstalker.com.br/perfil/"+perfil.getUsuario()+"\">http://www.bigstalker.com.br/perfil/"+perfil.getUsuario()+"</a>");
				mailService.enviarEmail(notificacao, "Big Stalker - Perfil "+perfil.getUsuario()+" encontrado!!",texto.toString());
				notificacao.setAlertado(true);
				notificacaoDAO.alterar(notificacao);
			}
		}
		
	}

}
