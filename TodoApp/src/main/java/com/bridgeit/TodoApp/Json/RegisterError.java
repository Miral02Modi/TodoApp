package com.bridgeit.TodoApp.Json;

import java.util.List;

import org.springframework.validation.FieldError;


public class RegisterError extends Response {
	
	private String errorName;
	private String errorEmail;
	private String errorPhone;
	private String errorPassword;
	private List<FieldError> list;
	
	
	
	public List<FieldError> getList() {
		return list;
	}
	public void setList(List<FieldError> list) {
		this.list = list;
	}
	
	
	public String getErrorName() {
		return errorName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	
	
	public String getErrorEmail() {
		return errorEmail;
	}
	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}
	
	
	public String getErrorPhone() {
		return errorPhone;
	}
	public void setErrorPhone(String errorPhone) {
		this.errorPhone = errorPhone;
	}
	
	
	public String getErrorPassword() {
		return errorPassword;
	}
	public void setErrorPassword(String errorPassword) {
		this.errorPassword = errorPassword;
	}
	
	
	@Override
	public String toString() {
		return "RegsiterError [errorName=" + errorName + ", errorEmail=" + errorEmail + ", errorPhone=" + errorPhone
				+ ", errorPassword=" + errorPassword + ", list=" + list + "]";
	}
	
	
}
