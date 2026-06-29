package com.hms.doctor.controller.request;

import jakarta.validation.constraints.NotNull;

public record CreateDoctorDTO (
		@NotNull(message = "hello first name is required")
		String firstName,
		String username,
		String middleName,
		String lastName,
		String email

) {}
