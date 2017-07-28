package com.bridgeit.TodoApp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.TodoApp.dao.TokenDao;
import com.bridgeit.TodoApp.model.Token;

public class TokenServiceImpl implements TokenService {

	@Autowired
	TokenDao dao1;

	@Override
	public Token addToken(Token token) throws Exception {
		return dao1.addToken(token);
	}

	@Override
	public Token getToken(String accesToken) {
		try {
			return dao1.getToken(accesToken);
		} catch (Exception e) {
			System.out.println(dao1);
			return null;
		}

	}

	@Override
	public Token deleteToken(Token token) throws Exception {
		return dao1.deleteToken(token);
	}
	
	
	/*public Token getToken1(String accesToken) {
		try {
			System.out.println("inside the service impl");
			return dao1.getToken1(accesToken);
		} catch (Exception e) {
			System.out.println(dao1);
			return null;
		}

	}*/

}
