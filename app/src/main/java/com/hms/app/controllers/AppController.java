package com.hms.app.controllers;

import com.hms.app.controllers.request.CreateHospitalDTO;
import com.hms.hospital.api.HospitalApi;
import com.hms.hospital.api.dtos.CreateHospitalApiRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/hospital/create")
	public ResponseEntity<String> createHospital(@RequestBody @Valid CreateHospitalDTO createHospitalDTO) {
		CreateHospitalApiRequestDTO createHospitalApiRequestDTO = CreateHospitalApiRequestDTO.builder()
				.name(createHospitalDTO.getName())
				.phone(createHospitalDTO.getPhone())
				.address(createHospitalDTO.getAddress())
				.build();
		hospitalApi.createHospital(createHospitalApiRequestDTO);
		return ResponseEntity.ok("hospital created");
	}
}
