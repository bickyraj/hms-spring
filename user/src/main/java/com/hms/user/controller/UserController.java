package com.hms.user.controller;

import com.hms.common.ApiResponse;
import com.hms.common.CustomPage;
import com.hms.user.controller.request.CreateUserDTO;
import com.hms.user.entity.User;
import com.hms.user.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Validated
public class UserController {

	private final UserService userService;
	private final ModelMapper modelMapper;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
		userService.createUser(modelMapper.map(createUserDTO, User.class));
		return ResponseEntity.ok(ApiResponse.builder()
				.message("user created successfully")
				.build());
	}

	@GetMapping("/list")
	public ResponseEntity<CustomPage<User>> list() {
		return ResponseEntity.ok(CustomPage.of(userService.getAllUsers(0, 10)));
	}
}
