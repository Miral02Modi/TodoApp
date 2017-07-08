package com.bridgeit.TodoApp.dao;


import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
public interface RegistrationDao {
	
	public void userRegister(UserRegistration registration) throws Exception;
	
	public UserRegistration loginUser(String email,String password) throws Exception;
}
