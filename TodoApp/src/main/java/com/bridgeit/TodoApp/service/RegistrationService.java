package com.bridgeit.TodoApp.service;

import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
public interface RegistrationService {
	
	public void userRegister(UserRegistration registration) throws Exception;
	
	public UserRegistration loginUser(String email,String password) throws Exception;
}
