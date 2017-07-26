package com.bridgeit.TodoApp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * It is 
 * 
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
	/**
	 * 
	 * This is code for the register the user
	 * 
	 * Registering  user information
	 * 
	 * @param registration {@link UserRegistration} all user Information from UI
	 * @param result  	   {@link BindingResult}  is used for the checking error It is Binding result
	 * @return  		   {@link ResponseEntity}
	 */
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Response> registerController(@RequestBody UserRegistration user,
			BindingResult result) {
		
		System.out.println("inside the register");
		
		Response response = new Response();
		validator.validate(user, result);

		
		// --------- Validation checking details of the registration
		if (result.hasErrors()) {

			List<FieldError> fieldErrors = result.getFieldErrors();
			RegisterError registerError = new RegisterError();

			registerError.setList(fieldErrors);
			registerError.setStatus(-1);
			registerError.setMessage("Invalid Credential please check your Field");

			return new ResponseEntity<Response>(registerError, HttpStatus.NOT_ACCEPTABLE);

		}
		

		// --------It give response to the client
		try {
			service.userRegister(user);
			response.setStatus(1);
			response.setMessage("Success");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println(e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Server fails");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	
	// -------------------------------Login-User--------------------------------------

	/**
	 * 
	 * 	 It is checking the login information from database 
	 * 
	 * @param loginMap   {@link Map}             It is used for get the email and password 
	 * @param result     {@link BindingResult}   It is use for error checking
	 * @param pRequest   {@link HttpServletRequest} 
	 * @param presponse  {@link HttpServletResponse}
	 * @return 
	 * @throws IOException Servlet request and response throws IO Exception
	 * 
	 *             
	 */

	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> loginController(@RequestBody Map<String, String> loginMap, BindingResult result,
			HttpServletRequest pRequest, HttpServletResponse presponse) throws IOException {
			
		System.out.println("Inside the login controlle gsdgsdgsdgsdgr");
		
		System.out.println("email"+loginMap.get("email"));
		System.out.println("password"+loginMap.get("password"));
		
	
		UserResponse userResponse = new UserResponse();

		// -----checking in database data valid user or not
		try {
			UserRegistration user = service.loginUser((String) loginMap.get("email"),
					(String) loginMap.get("password"));
			

			// -----Checking the data is available or not
			if (user == null) {

				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setStatus(-1);
				errorResponse.setMessage("Invalid User");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);

			}
			user.setPassword(null);
			
			// -----generating token
			Token token = new Token();
			token.setAccessToken(UUID.randomUUID().toString().replaceAll("-", ""));
			token.setRefreshToken(UUID.randomUUID().toString().replaceAll("-", ""));
			token.setUserId(user.getId());
			token.setCreateOn(new Date());
			tokenService.addToken(token);
				
			
			presponse.setHeader("accToken", token.getAccessToken());
			System.out.println("Login header:::::"+presponse.getHeader("accToken"));
			
			
			// -----set the cookie
			Cookie cookie = new Cookie("access_Token", token.getAccessToken());
			presponse.addCookie(cookie);
			presponse.setHeader("accssToken", token.getAccessToken());
			userResponse.setRegistration(user);
			userResponse.setStatus(1);
			userResponse.setMessage("Success");
			
			
			// -----set the session
			HttpSession httpSession = pRequest.getSession();
			//httpSession.invalidate();
			httpSession.setAttribute("user",user);
			
			
			return new ResponseEntity<Object>(token, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Server fails");
			return new ResponseEntity<Object>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	
	
	
	// -------------------------------Update-User-Profile-------------------------------------
	/**
	 * 
	 * Update the user information
	 * 
	 * @param registration  	{@link UserRegistration}
	 * @param result      	    {@link BindingResult}
	 * @return  			    {@link ResponseEntity}
	 */
	// -------------------------------Update-User-Profile-------------------------------------
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateProfile(@RequestBody UserRegistration registration, BindingResult result) {

		
		System.out.println("---------Inside the update---------------");
		

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
	/**
	 * @param id {@link String}
	 * @return	 {@link ResponseEntity}
	 */
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
