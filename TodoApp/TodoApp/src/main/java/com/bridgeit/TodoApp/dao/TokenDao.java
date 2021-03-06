package com.bridgeit.TodoApp.dao;

import com.bridgeit.TodoApp.model.Token;

/**
 * @author Miral
 *
 */
public interface TokenDao {
	
	public Token addToken(Token token) throws Exception;
	
	public Token getToken(String accesToken) throws Exception;
	
	public Token deleteToken(Token token) throws Exception;
	
	public Token getTokenByRefreshToken(String refreToken) throws Exception ;
	
	//public Token getToken1(String accesToken);
}
