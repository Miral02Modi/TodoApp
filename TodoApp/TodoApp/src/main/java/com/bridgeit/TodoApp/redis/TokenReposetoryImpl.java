/*package com.bridgeit.TodoApp.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bridgeit.TodoApp.model.Token;

@Repository
public class TokenReposetoryImpl implements TokenReposetory {

	private static final String KEY = "Token";

	private RedisTemplate<String, Token> redisTemplate;
	private HashOperations hashOps;

	
	public TokenReposetoryImpl() {
	}
	
	@Autowired
	private TokenReposetoryImpl(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOps = redisTemplate.opsForHash();
	}

	public void saveToken(Token token) {
		hashOps.put(KEY, token.getAccessToken(), token);
	}

	public Token findToken(String id) {
		return (Token) hashOps.get(KEY, id);
	}

}
*/