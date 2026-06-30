package com.hms.hospital.controller;

import com.hms.common.ApiResponse;
import com.hms.common.CustomPage;
import com.hms.hospital.dtos.CreateHospitalDTO;
import com.hms.hospital.entity.Hospital;
import com.hms.hospital.services.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
@Validated
public class HospitalController {

	private final HospitalService hospitalService;
	private final ModelMapper modelMapper;

	@GetMapping("/list")
	public ResponseEntity<CustomPage<Hospital>> list() {
		return ResponseEntity.ok(CustomPage.of(hospitalService.getAllHospitals(0, 10)));
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createHospital(@Valid @RequestBody CreateHospitalDTO createHospitalDTO) {
		hospitalService.createHospital(modelMapper.map(createHospitalDTO, Hospital.class));
		return ResponseEntity.ok(ApiResponse.builder()
				.message("hospital created").build());
	}
}
