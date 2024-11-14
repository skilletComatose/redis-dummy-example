package com.example.redis_example.redis;

import lombok.Builder;
import lombok.Getter;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "Person")
@Builder
@Getter
public class Person {
	@Id
	@Indexed
	private String cc;
	private String name;
	private Integer age;

	@With
	private String status;
}
