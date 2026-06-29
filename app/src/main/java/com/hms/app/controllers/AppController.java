package com.hms.app.controllers;

import com.hms.hospital.api.HospitalApi;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
public class AppController {
	private final HospitalApi hospitalApi;

	@GetMapping("/home")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("hello welcome");
	}
}
