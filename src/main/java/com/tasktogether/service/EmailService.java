package com.tasktogether.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tasktogether.model.User;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendVerification(User user) {
		String toAddress = user.getEmail();
		String fromAddress = "innovawatchcorporative@gmail.com";
		String senderName = "TaskTogether";
		String subject = "Registro exitoso";
		String content = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Bienvenido a nuestra plataforma</title>\r\n"
				+ "    <style>\r\n"
				+ "        body {\r\n"
				+ "            font-family: Arial, sans-serif;\r\n"
				+ "            background-color: #f4f4f4;\r\n"
				+ "            margin: 0;\r\n"
				+ "            padding: 0;\r\n"
				+ "        }\r\n"
				+ "        .container {\r\n"
				+ "            width: 100%;\r\n"
				+ "            max-width: 600px;\r\n"
				+ "            margin: 20px auto;\r\n"
				+ "            background: #ffffff;\r\n"
				+ "            padding: 20px;\r\n"
				+ "            border-radius: 10px;\r\n"
				+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
				+ "            text-align: center;\r\n"
				+ "        }\r\n"
				+ "        .button {\r\n"
				+ "            display: inline-block;\r\n"
				+ "            background-color: #28a745;\r\n"
				+ "            color: white;\r\n"
				+ "            padding: 10px 20px;\r\n"
				+ "            text-decoration: none;\r\n"
				+ "            border-radius: 5px;\r\n"
				+ "            font-size: 16px;\r\n"
				+ "            margin-top: 20px;\r\n"
				+ "        }\r\n"
				+ "    </style>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"container\">\r\n"
				+ "        <h2>¡Bienvenido a nuestra plataforma!</h2>\r\n"
				+ "        <p>Hola "+ user.getName() +" , gracias por registrarte. Estamos encantados de tenerte con nosotros.</p>\r\n"
				+ "        <p>Puedes acceder a tu cuenta haciendo clic en el siguiente botón:</p>\r\n"
				+ "        <p>Si tienes alguna pregunta, no dudes en contactarnos.</p>\r\n"
				+ "        <p>¡Saludos!<br>El equipo de TaskTogether</p>\r\n"
				+ "    </div>\r\n"
				+ "</body>\r\n"
				+ "</html>"; //Aquí sería el mensaje con código html
		
		 MimeMessage message = mailSender.createMimeMessage();
	     MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromAddress, senderName);
		    helper.setTo(toAddress);
		    helper.setSubject(subject);
		    helper.setText(content, true);
		    mailSender.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}
	
}
