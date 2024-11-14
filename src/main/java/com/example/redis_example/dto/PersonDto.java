package com.example.redis_example.dto;

import lombok.Builder;

@Builder
public record PersonDto(
		String name,
		Integer age,
		String cc,
		String status
) {
}
