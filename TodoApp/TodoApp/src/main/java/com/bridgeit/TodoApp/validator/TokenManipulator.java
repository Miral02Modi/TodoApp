package com.bridgeit.TodoApp.validator;

import java.util.Date;
import java.util.UUID;

public class TokenManipulator {

	
	String genrateAccessToken(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	String genrateRefreshToken(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	Date currenTime(){
		return new Date();
	}
	
	
	
}
