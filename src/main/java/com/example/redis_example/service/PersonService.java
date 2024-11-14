package com.example.redis_example.service;

import com.example.redis_example.dto.PersonDto;
import com.example.redis_example.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonService {
	private final RedisPersonService redisPersonService;
	private final PersonMapper personMapper;


	public PersonDto findPersonById(String id) {
		return redisPersonService.findByid(id)
				.map(personMapper::toPersonDto)
				.orElseThrow();
	}

	public PersonDto savePerson(PersonDto personDto) {
		return redisPersonService.savePerson(personDto);
	}

	public List<PersonDto> getAllPersons() {
		return redisPersonService.findAll()
				.map(personMapper::toPersonDto)
				.toList();
	}


}
