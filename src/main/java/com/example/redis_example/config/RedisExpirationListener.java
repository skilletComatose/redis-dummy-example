package com.example.redis_example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class RedisExpirationListener implements MessageListener {

	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String expiredKey = message.toString();
		String setKey = "Person";
		redisTemplate.opsForSet().remove(setKey, expiredKey.replace(setKey.concat(":"), ""));
		log.info("Expired key: {}" , expiredKey);
	}
}