package com.bridgeit.TodoApp.dao;


import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
public interface RegistrationDao {
	
	public void userRegister(UserRegistration registration,String url) throws Exception;
	
	public UserRegistration loginUser(String email,String password) throws Exception;
	
	public void updateProfile(UserRegistration registration) throws Exception;
	
	public UserRegistration getUserByID(String id) throws Exception;
	
	public UserRegistration  checkUserAvailable(String email) throws Exception;
	
}
