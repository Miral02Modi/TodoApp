package com.bridgeit.TodoApp.service;

import com.bridgeit.TodoApp.model.Token;

public interface TokenService {
	
	public Token addToken(Token token) throws Exception;
	
	public Token getToken(String accesToken);
	
	/*public Token getToken1(String accesToken);*/
}
