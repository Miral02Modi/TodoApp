package com.bridgeit.TodoApp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TodoApp.model.Picture;
import com.bridgeit.TodoApp.model.Token;
import com.bridgeit.TodoApp.model.UserRegistration;
import com.bridgeit.TodoApp.service.GmailUtil;
import com.bridgeit.TodoApp.service.RegistrationService;
import com.bridgeit.TodoApp.service.TokenService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
public class GmailController {

	@Autowired
	TokenService tokenService;
	@Autowired
	RegistrationService service;

	@RequestMapping(value = "/loginWithGmailData")
	public void facbookSigin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userId = UUID.randomUUID().toString();
		request.getSession().setAttribute("State", userId);
		
		System.out.println("Gmail login");
		String GmailUrl = GmailUtil.getGmailURl();
		response.sendRedirect(GmailUrl);
	}

	@RequestMapping(value = "redirectGoogle")
	public void gmailUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String googleCode = request.getParameter("code");
		System.out.println("googleRedirect login :: " + googleCode);

		String googleToken = GmailUtil.gmailAccessToken(googleCode);
		System.out.println("google access token :: " + googleToken);

		// ------------------------------------------------------------------------------
		JsonNode googleProfileData = GmailUtil.googleProfile(googleToken);
		UserRegistration user = new UserRegistration();

		
		
		
		user.setName(googleProfileData.get("displayName").asText());
		user.setEmail(googleProfileData.get("emails").get(0).get("value").asText());
		String image = googleProfileData.get("image").get("url").asText();
		HttpSession session = request.getSession();
		
		try {
			service.userRegister(user, "facebook");
			session.setAttribute("user", user);
			System.out.println("userId is :: " + user.getId());
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
		String refreshToken = UUID.randomUUID().toString().replaceAll("-", "");
		Token token = new Token();
		token.setCreateOn(new Date());;
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		token.setUserId(user.getId());
		try {
			tokenService.addToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("token", token);

		System.out.println("hiii............");

		response.sendRedirect("http://localhost:8080/TodoApp/#!/fbRedirect?tokenData=token");
	}

}
