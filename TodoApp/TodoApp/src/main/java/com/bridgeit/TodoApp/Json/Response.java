package com.bridgeit.TodoApp.Json;

import com.bridgeit.TodoApp.model.UserRegistration;

public class Response {

	int status;
	String message;
	UserRegistration user;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public UserRegistration getUser() {
		return user;
	}

	public void setUser(UserRegistration user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + "]";
	}

}
