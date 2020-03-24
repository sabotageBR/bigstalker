package com.bigstalker.service.mail;

import java.util.Properties;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.bigstalker.entity.Notificacao;
import com.bigstalker.util.UtilEmail;

@Stateless
public class MailService {


	@Asynchronous
	public void enviar(String titulo, String texto, String...destinatarios){
	}
	
	
	@Asynchronous
	public void enviarEmail(Notificacao notificacao, String titulo,String texto) {
			Properties props = new Properties();
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("helpwstalker@gmail.com", "g34rtkm8");
				}
			});
			try {
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("bigstalker"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(notificacao.getEmailAlerta()));
					message.setSubject(titulo);
					BodyPart messageBodyPart = new MimeBodyPart();
					messageBodyPart.setContent(UtilEmail.corpo
							.replace("@@TEXTO", texto)
							.replace("@@SIGLA_EMPRESA", "Big Stalker")
							.replace("@@TELEFONE", "www.bigstalker.com"),"text/html");
					
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);
					messageBodyPart = new MimeBodyPart();
					multipart.addBodyPart(messageBodyPart);
					message.setContent(multipart);
					Transport.send(message);
					System.out.println(notificacao.getEmailAlerta() +" - Sent message successfully....");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		
	}

}