package com.bridgeit.TodoApp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TodoApp.Json.ErrorResponse;
import com.bridgeit.TodoApp.model.FaceBookProfile;
import com.bridgeit.TodoApp.model.Token;
import com.bridgeit.TodoApp.model.UserRegistration;
import com.bridgeit.TodoApp.service.FacebookUtil;
import com.bridgeit.TodoApp.service.RegistrationService;
import com.bridgeit.TodoApp.service.TokenService;

@RestController
public class FacebookController {

	@Autowired
	TokenService tokenService;
	@Autowired
	RegistrationService service;

	FacebookUtil facebookUtil = new FacebookUtil();

	@RequestMapping(value = "/loginWithFB")
	public void facbookSigin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userId = UUID.randomUUID().toString();
		request.getSession().setAttribute("State", userId);

		String facebookUrl = facebookUtil.getFacebookURl();
		response.sendRedirect(facebookUrl);
	}

	@RequestMapping(value = "/redirectFBURL", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Object> redirectFacebook(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String state = request.getParameter("state");
		System.out.println("parameter is::" + state);
		String code = request.getParameter("code");
		System.out.println("Code is" + code);

		if (code == null || code == "") {

			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Invalid User");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
		}

		String accessToken = facebookUtil.getFBAccessToken(code);
		Token token = new Token();

		try {

			token.setAccessToken(accessToken);
			token.setRefreshToken(UUID.randomUUID().toString().replaceAll("-", ""));
			token.setCreateOn(new Date());
			response.setHeader("accToken", accessToken);

			tokenService.addToken(token);
			FaceBookProfile profile = FacebookUtil.getFBProfile(accessToken);
			UserRegistration user1 = service.checkUserAvailable(profile.getEmail());
			HttpSession session = request.getSession();
			
			if (user1 == null) {
				// ----------Setting value in User Class
				UserRegistration user = new UserRegistration();
				user.setEmail(profile.getEmail());
				user.setName(profile.getFirst_name());

				// ----------Set To the User Database
				service.userRegister(user, "facebook");

				// ----------Set Value in Session
				session.setAttribute("user", user);
				response.sendRedirect("http://localhost:8080/TodoApp/#!/todoHome");
			}
			
			session.setAttribute("user", user1);
			response.sendRedirect("http://localhost:8080/TodoApp/#!/todoHome");
			return new ResponseEntity<Object>(token, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Invalid User");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
