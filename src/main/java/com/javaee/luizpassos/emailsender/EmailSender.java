package com.javaee.luizpassos.emailsender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSender {

	private String subject;
	private String body;
	private String toEmail;
	
	private String fromEmail;
	private String password;
	
	
	private EmailConfig emailConfig;
	
	public EmailSender() {
		this.emailConfig = new EmailConfig();
		
		this.fromEmail = "example@gmail.com";
		this.password = "*******";
	}	
		
	
	public void send() {
		if (this.subject != null && this.subject.isEmpty()) {
            throw new IllegalArgumentException("Assunto não foi informado.");
        }
		
		if (this.body!= null && this.body.isEmpty()) {
            throw new IllegalArgumentException("Corpo da Mensagem está em branco");
        }
		
		if (this.toEmail != null && this.toEmail.isEmpty()) {
            throw new IllegalArgumentException("Destinatário não foi informado");
        }
		
		System.out.println("Enviando email");
		
		this.emailConfig.sendEmail(this.fromEmail, 
				this.password, this.toEmail, this.subject, this.body);
		
		System.out.println("Email enviado");
	}


}
