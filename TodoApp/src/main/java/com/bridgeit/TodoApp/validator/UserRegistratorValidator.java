package com.bridgeit.TodoApp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bridgeit.TodoApp.Json.RegisterError;
import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
@Component
public class UserRegistratorValidator implements Validator {
	
	RegisterError registerError = new RegisterError();
	private static final String PASSWORD_PATTERN = "^[A-Za-z][A-Za-z0-9@#%&*]*$";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserRegistration.class.isAssignableFrom(clazz);
	}

	
	
	// --------------------------User-Registration-Validation---------------------------------------
	@Override
	public void validate(Object target, Errors error) {

		UserRegistration registration = (UserRegistration) target;
		
		
		// Empty email validation
		ValidationUtils.rejectIfEmpty(error, "email", "email.required", "Email must be required");
		// Empty password validation
		ValidationUtils.rejectIfEmpty(error, "password", "password.required", "Password Must be required");
		// Empty phone validation
		ValidationUtils.rejectIfEmpty(error, "phone", "phone.required", "Phone Must be required");

		
		
		// -----------------------Email validation pattern----------
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		String email = registration.getEmail();
		Matcher matcher = pattern.matcher(email);
		System.out.println(matcher.matches());
		
		if (!matcher.matches())
			error.rejectValue("email", "Please enter the valid emailID");
		

		// -----------------------mobile validation pattern----------
		String mobileNumber = "^(?:0091|\\+91|0)[7-9][0-9]{9}$";
		Pattern pattern2 = Pattern.compile(mobileNumber);
		String phone =""+registration.getPhone();
		Matcher matcher2 = pattern2.matcher(phone);
		
		
		if (!matcher2.matches())
			error.reject("phone", "Please enter 10digit number");
		
		
		// -----------------------password validation pattern----------
		Pattern pattern3 = Pattern.compile("^[A-Za-z][A-Za-z0-9@#%&*]*$");
		Matcher matcher3 = pattern.matcher(registration.getPassword());
		
		if(!matcher3.matches())
			error.reject("password","password must be strong");
		
	}

	
	
	
	// --------------------------User-Login-Validation------------------------------------------------
	public void validateLogin(Object target, Errors error) {

		//UserRegistration registration = (UserRegistration) target;
		UserRegistration registration = (UserRegistration) target;
		
		
		// Empty email validation
		ValidationUtils.rejectIfEmpty(error, "email", "email.required", "Email must required");
		// Empty password validation
		ValidationUtils.rejectIfEmpty(error, "password", "password.required", "Password Must required");

		// Email validation pattern
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		String email = registration.getEmail();
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches())
			error.rejectValue("email", "Please enter the valid emailID");
	}

}
