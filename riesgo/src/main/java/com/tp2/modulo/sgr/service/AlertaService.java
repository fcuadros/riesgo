package com.tp2.modulo.sgr.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.tp2.modulo.sgr.dao.AlertaDAO;
import com.tp2.modulo.sgr.model.Correo;;

public class AlertaService {
	
		AlertaDAO AlertaDAO = new AlertaDAO();
		
//		@Autowired
		public JavaMailSender emailSender;
		
		public void sendSimpleMessage(String to, String subject, String text) {
			try {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(to);
				message.setSubject(subject);
				message.setText(text);

				emailSender.send(message);
			} catch (MailException exception) {
				exception.printStackTrace();
			}
		}

		public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template,
			String... templateArgs) {
			String text = String.format(template.getText(), templateArgs);
			sendSimpleMessage(to, subject, text);
		}

		public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
			try {
				MimeMessage message = emailSender.createMimeMessage();
				// pass 'true' to the constructor to create a multipart message
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(text);

				FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
				helper.addAttachment("Invoice", file);

				emailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		public void sendSimpleMessagesv2(String to, String subject, String text, List<Correo> co) {
			try {
				
				String body ="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head></head><body><style></style><article class=\"bodymail\" style=\"background-color:#eee;padding:10px 20px\"><h2 class=\"mailtitle\" style=\"color:#333;font-size:20px;text-align:center\">Modulo Riesgos</h2><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\">Notificación de riesgos, usted recibe este correo por estar asignado como responsable de ...</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\">" + text + "</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Nombre del riesgo:</b> nombre del riesgo</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Descripción:</b> descripción del riesgo</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Persona que identificó:</b> Omar Diaz</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Fecha de registro:</b> 15/11/2018</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Tipo de escala:</b> Costo</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Nivel del riesgo:</b> Medio</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Costo:</b> S/ 500.00</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><b>Probabilidad:</b> 0.8</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\">&nbsp;</p><p class=\"mailparagraph\" style=\"font-size:14px;margin:10px\"><img src=\"http://riesgos-backend.appspot.com/img/logo.png\" alt=\"\"></p></article></body></html>";
				 
				JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
				
//				emailSender.setUsername("testmail@nextsmart.net");
//				emailSender.setPassword("X.W.~GICK@*)RPIAV]");
				emailSender.setUsername("riesgos-api@sandboxef21ca99a1644f77b4087f19cb8c6fed.mailgun.org");
				emailSender.setPassword("123456789");
				
				Properties properties = new Properties();
			    properties.setProperty("mail.transport.protocol", "smtp");
			    properties.setProperty("mail.smtp.auth", "true");
			    properties.setProperty("mail.smtp.starttls.enable", "false");
//			    properties.setProperty("mail.debug", Boolean.toString(this.debug));
//			    properties.setProperty("mail.smtp.host", "nextsmartservers.com");
			    properties.setProperty("mail.smtp.host", "smtp.mailgun.org");
			    properties.setProperty("mail.smtp.port", "25");
//			    properties.setProperty("mail.smtp.ssl.trust", this.trust);
			    emailSender.setJavaMailProperties(properties);
				
				
				MimeMessage message = emailSender.createMimeMessage(); 
//				MimeMessageHelper helper = new MimeMessageHelper(message);
				MimeMessageHelper helper = new MimeMessageHelper(message,true);
				
				message.setContent(body, "text/html");

//				SimpleMailMessage message = new SimpleMailMessage();
				try {
//					helper.setFrom(getProp("spring.mail.from_global"),getProp("spring.mail.from_global_name"));
//					helper.setFrom("djuarez7@hotmail.com","Administrador de correos");
					helper.setFrom("riesgos-api@sandboxef21ca99a1644f77b4087f19cb8c6fed.mailgun.org","Administrador de correos");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				helper.setTo(to);
				
				
				//COPIAS:
				try {
					if(!co.isEmpty()) {
						for (Correo mail : co) {
							helper.addCc(mail.getMail());
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				helper.setSubject(subject);
				helper.setText(text, true);
				

				emailSender.send(message);
			} catch (MailException | MessagingException exception) {
				exception.printStackTrace();
			}
		}

	}
