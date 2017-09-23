package com.bridgeit.TodoApp.redis;

import com.bridgeit.TodoApp.model.Token;

public interface TokenReposetory {

	void saveToken(Token token);

	Token findToken(String id);
}
