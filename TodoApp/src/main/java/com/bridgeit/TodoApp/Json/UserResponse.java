package com.bridgeit.TodoApp.Json;

import java.util.List;

import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
public class UserResponse extends Response {
	
	List<UserRegistration> userRegistrations;
	UserRegistration registration;
	
	
	public UserRegistration getRegistration() {
		return registration;
	}
	public void setRegistration(UserRegistration registration) {
		this.registration = registration;
	}
	
	
	public List<UserRegistration> getUserRegistrations() {
		return userRegistrations;
	}
	public void setUserRegistrations(List<UserRegistration> userRegistrations) {
		this.userRegistrations = userRegistrations;
	}
	
	
	@Override
	public String toString() {
		return "UserResponse [userRegistrations=" + userRegistrations + ", registration=" + registration + "]";
	}
	
	
	
}
