package com.bridgeit.TodoApp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TodoApp.Json.ErrorResponse;
import com.bridgeit.TodoApp.Json.RegisterError;
import com.bridgeit.TodoApp.Json.Response;
import com.bridgeit.TodoApp.Json.UserResponse;
import com.bridgeit.TodoApp.model.Token;
import com.bridgeit.TodoApp.model.UserRegistration;
import com.bridgeit.TodoApp.service.RegistrationService;
import com.bridgeit.TodoApp.service.TokenService;
import com.bridgeit.TodoApp.validator.UserRegistratorValidator;

/**
 * @author Miral
 *
 */
@RestController
public class RegistrationController {

	@Autowired
	RegistrationService service;
	@Autowired
	UserRegistratorValidator validator;
	@Autowired
	TokenService tokenService;

	// ----------------------------UserRegistration-----------------------------
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Response> registerController(@RequestBody UserRegistration registration,
			BindingResult result) {

		Response response = new Response();
		validator.validate(registration, result);

		// --------- Validation checking details of the registration
		if (result.hasErrors()) {

			List<FieldError> fieldErrors = result.getFieldErrors();
			RegisterError registerError = new RegisterError();

			registerError.setList(fieldErrors);
			registerError.setStatus(-1);
			registerError.setMessage("Invalid Credential please check your Field");

			return new ResponseEntity<Response>(registerError, HttpStatus.NOT_ACCEPTABLE);

		}

		// --------Checking it is successfully register in database
		try {
			service.userRegister(registration);
			response.setStatus(1);
			response.setMessage("Success");
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Server fails");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// -------------------------------Login-User--------------------------------------

	/**
	 * @param loginMap
	 * @param result
	 * @param pRequest
	 * @param presponse
	 * @return
	 * @throws IOException
	 * 
	 *             It is checking the login information
	 */

	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> loginController(@RequestBody Map<String, String> loginMap, BindingResult result,
			HttpServletRequest pRequest, HttpServletResponse presponse) throws IOException {

		UserRegistration registration1 = new UserRegistration();
		registration1.setEmail(loginMap.get("email"));
		registration1.setPassword(loginMap.get("password"));

		UserResponse userResponse = new UserResponse();

		// -----server side the validation for login
		if (result.hasErrors()) {

			System.out.println("inside the has error");
			List<FieldError> fieldErrors = result.getFieldErrors();
			RegisterError registerError = new RegisterError();

			registerError.setList(fieldErrors);
			registerError.setStatus(-1);
			registerError.setMessage("Invalid Credential please check your Field");

			return new ResponseEntity<Response>(registerError, HttpStatus.NOT_ACCEPTABLE);
		}

		// -----checking in database data valid user or not
		try {
			UserRegistration registration = service.loginUser((String) loginMap.get("email"),
					(String) loginMap.get("password"));

			// -----Checking the data is available or not
			if (registration == null) {

				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setStatus(1);
				errorResponse.setMessage("Invalid User");
				return new ResponseEntity<Response>(errorResponse, HttpStatus.NOT_FOUND);

			}

			// -----generating token
			Token token = new Token();
			token.setAccessToken(UUID.randomUUID().toString().replaceAll("-", ""));
			token.setRefreshToken(UUID.randomUUID().toString().replaceAll("-", ""));
			token.setUserId(registration.getId());
			token.setCreateOn(new Date());
			tokenService.addToken(token);

			// -----set the cookie
			Cookie cookie = new Cookie("access_Token", token.getAccessToken());
			presponse.addCookie(cookie);

			userResponse.setRegistration(registration);
			userResponse.setStatus(1);
			userResponse.setMessage("Success");
			return new ResponseEntity<Response>(userResponse, HttpStatus.OK);

		} catch (Exception e) {

			System.out.println(e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Server fails");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	// -------------------------------Update-User-Profile-------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateProfile(@RequestBody UserRegistration registration, BindingResult result) {

		System.out.println("Inside the update");

		ErrorResponse errorResponse = new ErrorResponse();
		Response response = new Response();
		validator.validate(registration, result);

		if (result.hasErrors()) {
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Validation problem");
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

		try {
			service.updateProfile(registration);
			response.setStatus(1);
			response.setMessage("Success update");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception exception) {
			errorResponse.setStatus(1);
			errorResponse.setMessage("Success update");
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// -------------------------------get-User-By-Id-------------------------------------
	@RequestMapping(value = "{id}")
	public ResponseEntity<Void> getUserByID(@PathVariable("id") String id) {

		try {
			service.getUserbyId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
