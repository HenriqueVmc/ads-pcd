package pct;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


//Biblioteca necessaria:
//https://javaee.github.io/javamail/ -> javax.mail.jar

// Habilitar envio smtp no gmail
//https://myaccount.google.com/lesssecureapps

public class TesteDeEmail {

	private static String username;
	private static String pass;
	private static String to;
	private static String assunto;
	private static String msg;
	
	public TesteDeEmail(String email, String senha, String to, String assunto, String message) {
		// TODO Auto-generated constructor stub
		this.username = email;
		this.pass = senha;
		this.to = to; 
		this.assunto = assunto;
		this.msg = message;
	}
	
	public boolean Enviar() {
		Boolean status = false;
		// TODO Auto-generated method stub
		String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.debug", "true");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, pass);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // para si mesmo
			message.setSubject(assunto);
			message.setText(msg);			

			Transport.send(message);
			System.out.println("Feito");
			 
			status = true;

		} catch (MessagingException e) {
			status = false;
			throw new RuntimeException(e);
		}
		
		return status;
	}
}

