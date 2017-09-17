package com.bridgeit.TodoApp.service;

import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
public interface RegistrationService {
	
	public int userRegister(UserRegistration registration,String url) throws Exception;
	
	public UserRegistration loginUser(String email,String password) throws Exception;
	
	public void updateProfile(UserRegistration registration) throws Exception;
	
	public UserRegistration getUserbyId(String id) throws Exception;
	
	public UserRegistration  checkUserAvailable(String email) throws Exception;
	
	public void  verifyMail(int varifyUser) throws Exception;
	
	public void updatePassword(String password,int id) throws Exception ;
	
	public int getUserId(String email) throws Exception ;
	
	public void updateImage(UserRegistration user) throws Exception;
}
