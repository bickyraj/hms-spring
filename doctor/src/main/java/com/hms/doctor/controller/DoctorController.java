package com.hms.doctor.controller;

import com.hms.common.CustomPage;
import com.hms.doctor.application.CreateDoctorUseCase;
import com.hms.doctor.controller.request.CreateDoctorDTO;
import com.hms.doctor.entity.Doctor;
import com.hms.doctor.services.DoctorService;
import com.hms.hospital.entity.Hospital;
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
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@Validated
public class DoctorController {

	private final CreateDoctorUseCase createDoctorUseCase;
	private final DoctorService doctorService;

	@GetMapping("")
	public ResponseEntity<String> home() {
		return ResponseEntity.ok("welcome to doctor");
	}

	@GetMapping("/list")
	public ResponseEntity<CustomPage<Doctor>> list() {
		return ResponseEntity.ok(CustomPage.of(doctorService.getAllDoctors(0, 10)));
	}

	@PostMapping("/create")
	public ResponseEntity<String> createDoctor(@RequestBody @Valid CreateDoctorDTO createDoctorDTO) {
		CreateDoctorUseCase.Response response = createDoctorUseCase
				.execute(CreateDoctorUseCase.Request.of(
						createDoctorDTO.firstName(),
						createDoctorDTO.middleName(),
						createDoctorDTO.lastName(),
						createDoctorDTO.email(),
						createDoctorDTO.username()
				));

		return ResponseEntity.ok(response.getMessage());
	}
}
