package com.example.redis_example.repository;

import com.example.redis_example.redis.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisPersonRepository extends CrudRepository<Person, String> {
	@Override
	List<Person> findAll();
}
