package com.bridgeit.TodoApp.Json;



public class Response {
	
	int status;
	String message;
	
	
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
	
	
	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + "]";
	}
	
	
	
}
