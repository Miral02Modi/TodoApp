package com.bridgeit.TodoApp.controller;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bridgeit.TodoApp.Json.TokenResponse;
import com.bridgeit.TodoApp.model.Token;
import com.bridgeit.TodoApp.service.TokenService;

@Controller
public class ReFreshValiadate {

	@Autowired
	TokenService tokenService;

	/**
	 * 
	 * This is called by client if the request token is expired
	 * 
	 * @param refreshToken
	 * @param pRequest
	 * @param pResponse
	 * @return
	 */

	@RequestMapping(value = "/refreshToken",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> refreshValidation(@RequestBody String refreshToken, HttpServletRequest pRequest,
			HttpServletResponse pResponse) {

		System.out.println("Inside the refresh Token"+refreshToken);
		/*String refreshToken = refreshToken1.get("refreshToken");*/
		/*
		 * String refreshToken = token.getRefreshToken(); Date oldDate =
		 * token.getCreateOn(); Date currentDate = new Date(); long diffence =
		 * currentDate.getTime() - oldDate.getTime(); long diffenceInSecond =
		 * TimeUnit.MILLISECONDS.toSeconds(diffence);
		 */

		try {

			Token token;
			token = tokenService.getTokenByRefreshToken(refreshToken);

			// -----If the refreshToken is Not Found
			if (token == null) {

				TokenResponse response = new TokenResponse();
				response.setMessage("Invalid refresh Token");
				response.setStatus(-1);
				return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);

			}

			Date oldDate = token.getCreateOn();
			Date currentDate = new Date();
			long diffence = currentDate.getTime() - oldDate.getTime();
			long diffenceInSecond = TimeUnit.MILLISECONDS.toSeconds(diffence);

			if (diffenceInSecond <= 2 * 60) { // 60 days

				Token token2 = new Token();
				token2.setAccessToken(UUID.randomUUID().toString().replaceAll("-", ""));
				token2.setRefreshToken(UUID.randomUUID().toString().replaceAll("-", ""));
				token2.setCreateOn(new Date());

				tokenService.addToken(token2);
				pResponse.setHeader("accToken", token2.getAccessToken());
				return new ResponseEntity<Object>(token2, HttpStatus.OK);

			}
			
			
			System.out.println("Refresh token expired");
			TokenResponse response = new TokenResponse();
			response.setMessage("Refresh Token has Been Expired");
			response.setStatus(-1);
			
			return new ResponseEntity<Object>(response, HttpStatus.NOT_ACCEPTABLE);

		} catch (Exception e) {
			e.printStackTrace();
			TokenResponse response = new TokenResponse();
			response.setMessage("Refresh Token has Been Expired");
			response.setStatus(-1);

			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
