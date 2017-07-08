package com.bridgeit.TodoApp.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.TodoApp.Json.ErrorResponse;
import com.bridgeit.TodoApp.Json.RegisterError;
import com.bridgeit.TodoApp.Json.Response;
import com.bridgeit.TodoApp.Json.UserResponse;
import com.bridgeit.TodoApp.model.UserRegistration;
import com.bridgeit.TodoApp.service.RegistrationService;
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

	// ----------------------------UserRegistration-----------------------------
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Response> registerController(@RequestBody UserRegistration registration,
			BindingResult result) {

		Response response = new Response();
		validator.validate(registration, result);

		
		//--------- Validation checking details of the registration
		if (result.hasErrors()) {

			List<FieldError> fieldErrors = result.getFieldErrors();
			RegisterError registerError = new RegisterError();

			registerError.setList(fieldErrors);
			registerError.setStatus(-1);
			registerError.setMessage("Invalid Credential please check your Field");

			return new ResponseEntity<Response>(registerError, HttpStatus.NOT_ACCEPTABLE);

		}

		//--------Checking it is successfully register in database
		try {
			service.userRegister(registration);
			response.setStatus(1);
			response.setMessage("Success");
			return new ResponseEntity<Response>(HttpStatus.OK);
		} catch (Exception e) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Server fails");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.BAD_REQUEST);
		}

	}

	
	
	
	//-------------------------------Login-User--------------------------------------
	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> loginController(@RequestBody Map<String,String> loginMap, BindingResult result) {
		
		UserRegistration registration1 = new UserRegistration();
		registration1.setEmail(loginMap.get("email"));
		registration1.setPassword(loginMap.get("password"));
		
		UserResponse response = new UserResponse();
		System.out.println(loginMap);
		//validator.validateLogin(registration1, result);

		
		//-----server side the validation for login
		if (result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			RegisterError registerError = new RegisterError();

			registerError.setList(fieldErrors);
			registerError.setStatus(-1);
			registerError.setMessage("Invalid Credential please check your Field");

			return new ResponseEntity<Response>(registerError, HttpStatus.NOT_ACCEPTABLE);
		}

		
		
		//-----checking in database data valid user or not 
		try {
			UserRegistration registration = service.loginUser((String)loginMap.get("email"),(String)loginMap.get("password"));
			
			
			
			//-----Checking the data is available or not
			if(registration == null){
				System.out.println("Inside the null");
				response.setRegistration(registration);
				response.setStatus(1);
				response.setMessage("Invalid User");
				return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
			}
			
			response.setRegistration(registration);
			response.setStatus(1);
			response.setMessage("Success");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			
			System.out.println(e);
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("Server fails");
			return new ResponseEntity<Response>(errorResponse, HttpStatus.BAD_REQUEST);
			
		}
		
		
		
	}
}
