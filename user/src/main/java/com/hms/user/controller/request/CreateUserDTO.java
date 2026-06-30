package com.hms.user.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class CreateUserDTO {

	@NotNull
	private final String firstName;

	@NotNull(message = "email is required")
	private final String email;

	@NotNull
	private final String username;

	private final String middleName;
	private final String lastName;
	private final String phone;
	private final String address;
}
