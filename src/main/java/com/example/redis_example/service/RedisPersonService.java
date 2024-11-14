package com.example.redis_example.service;

import com.example.redis_example.dto.PersonDto;
import com.example.redis_example.mapper.PersonMapper;
import com.example.redis_example.redis.Person;
import com.example.redis_example.repository.RedisPersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RedisPersonService {
	private final RedisPersonRepository redisPersonRepository;
	private final ObjectMapper objectMapper;
	private final PersonMapper personMapper;

	public Optional<Person> findByid(String id) {
		return redisPersonRepository.findById(id);
	}

	public Stream<Person> findAll() {
		return redisPersonRepository.findAll()
				.stream();
	}

	public PersonDto savePerson(PersonDto personDto) {
		Person entity = redisPersonRepository.save(
				personMapper.toPerson(personDto)
		);
		return personMapper.toPersonDto(entity);
	}
}
