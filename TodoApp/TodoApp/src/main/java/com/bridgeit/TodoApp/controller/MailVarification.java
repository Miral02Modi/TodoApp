package com.bridgeit.TodoApp.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TodoApp.service.RegistrationService;

@RestController
public class MailVarification {

	@Autowired
	RegistrationService service;

	public static void varifyMail(String email, String token) {

		final String username = "demorajwar@gmail.com";
		final String password = "rajwar@123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			System.out.println("token is:::"+token);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Html Subject");
			message.setText("Please verify your account");
			message.setContent(
					"<a href='http://localhost:8080/TodoApp/activeUser?&Email=" + token+"'" + ">Click Here</a>",
					"text/html");
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/activeUser", method = { RequestMethod.GET, RequestMethod.POST })
	public void reDirectGmailVerify(HttpServletRequest request, HttpServletResponse response) {

		String code = request.getParameter("Email");
		System.out.println("code is::::" + code);
		String id = (String) request.getSession().getAttribute(code);
		System.out.println("email id is:::"+id);
		int userId = Integer.parseInt(id);
		if (id != null) {
			try {
				service.verifyMail(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			response.sendRedirect("http://localhost:8080/TodoApp/#!/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
