package com.example.redis_example.config;


import com.example.redis_example.redis.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.KeyspaceConfiguration;
import org.springframework.data.redis.core.convert.MappingConfiguration;
import org.springframework.data.redis.core.index.IndexConfiguration;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfiguration {

	@Value("${person.ttl.seconds}")
	private Long personTtlinSecods;


	@Value("${redis.host}")
	public String redisHost;

	@Value("${redis.port}")
	public int redisPort;

	@Value("${redis.password}")
	public String redisPassword;


	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory factory = new LettuceConnectionFactory(redisHost, redisPort);
		factory.setPassword(redisPassword);
		return factory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);

		template.setKeySerializer(new StringRedisSerializer());

		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}


	@Bean
	public RedisMappingContext keyValueMappingContext() {
		MyKeyspaceConfiguration.setImageSingExpirationTime(personTtlinSecods);
		return new RedisMappingContext(new MappingConfiguration(new IndexConfiguration(), new MyKeyspaceConfiguration()));
	}

	public static class MyKeyspaceConfiguration extends KeyspaceConfiguration {

		private static Long imageSingExpirationTime;

		private static void setImageSingExpirationTime(Long value) {
			imageSingExpirationTime = value;
		}

		@Override
		protected Iterable<KeyspaceSettings> initialConfiguration() {
			KeyspaceSettings keyspaceSettings = new KeyspaceSettings(Person.class, "Person");
			keyspaceSettings.setTimeToLive(imageSingExpirationTime);
			return List.of(keyspaceSettings);
		}
	}
}