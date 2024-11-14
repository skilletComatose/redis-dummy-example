package com.example.redis_example.controller;


import com.example.redis_example.dto.PersonDto;
import com.example.redis_example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/persons")
public class PersonController {

    private final PersonService personService;

	@GetMapping("/{id}")
	public ResponseEntity<PersonDto> findPersonById(@PathVariable String id) {
		return ResponseEntity.ok(personService.findPersonById(id));
	}

	@PostMapping("")
	public ResponseEntity<PersonDto> savePerson(@RequestBody PersonDto request) {
		return ResponseEntity.ok(personService.savePerson(request));
	}

	@GetMapping("")
	public ResponseEntity<List<PersonDto>> getProducts() {
		return ResponseEntity.ok(personService.getAllPersons());
	}
}
