/*package com.bridgeit.TodoApp.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {
		
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		System.out.println("Inside the connection");
	   JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
	   jedisConFactory.setHostName("localhost");
	   jedisConFactory.setPort(6379);
	   return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	   RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	   template.setConnectionFactory(jedisConnectionFactory());
	   return template;
	}
	
}*/
