package com.proceso.mail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.proceso.utiles.Constantes;

import jxl.common.Logger;

public class EnviarMail {

	String miCorreo = "";
	String miContrasena = "";
	String servidorSMTP = "";
	String puertoEnvio = "";
	String mailReceptor = null;
	String asunto = null;
	String cuerpo = null;
	private static Logger log = Logger.getLogger(EnviarMail.class);

	public EnviarMail(String asunto, String cuerpo) {
		this.asunto = asunto;
		this.cuerpo = cuerpo;

		Properties prop = new Properties();
		InputStream is = null;
		String[] correos = null;

		try {
			is = new FileInputStream(Constantes.PROPERTIES_CONFIGURACION);
			prop.load(is);

			Properties props = new Properties();
			miContrasena = prop.getProperty("mail.smtp.pass");
			miCorreo = prop.getProperty("mail.smtp.user");

			props.put("mail.smtp.user", prop.getProperty("mail.smtp.user"));
			props.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
			props.put("mail.smtp.port", prop.getProperty("mail.smtp.port"));
			props.put("mail.smtp.starttls.enable", prop.getProperty("mail.smtp.starttls.enable"));
			props.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
			props.put("mail.smtp.socketFactory.port", prop.getProperty("mail.smtp.socketFactory.port"));
			props.put("mail.smtp.socketFactory.class", prop.getProperty("mail.smtp.socketFactory.class"));
			props.put("mail.smtp.socketFactory.fallback", prop.getProperty("mail.smtp.socketFactory.fallback"));

			SecurityManager security = System.getSecurityManager();

			correos = prop.getProperty("CORREOS").split(",");

			log.debug(correos);

			Authenticator auth = new autentificadorSMTP();
			Session session = Session.getInstance(props, auth);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(cuerpo, "UTF-8", "html");
			msg.setSubject(asunto);
			msg.setFrom(new InternetAddress(miCorreo));

			for (int i = 0; i < correos.length; i++) {
				log.debug("Mensaje Correo para: " + correos[i]);
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(correos[i]));
			}

			// msg.addRecipients(Message.RecipientType.CC, new InternetAddress[] { new InternetAddress(correos[1]) });

			Transport.send(msg);

		} catch (Exception e) {
			log.error("Error enviando mail " + e.getMessage(), e);
		}

	}

	private class autentificadorSMTP extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(miCorreo, miContrasena);
		}
	}

}
