package com.example.redis_example.mapper;

import com.example.redis_example.dto.PersonDto;
import com.example.redis_example.redis.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  PersonMapper {
	PersonDto toPersonDto(Person person);
	Person toPerson(PersonDto personDto);
}
