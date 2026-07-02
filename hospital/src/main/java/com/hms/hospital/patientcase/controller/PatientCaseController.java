package com.hms.hospital.patientcase.controller;

import com.hms.common.CustomPage;
import com.hms.common.constants.PatientCaseStatus;
import com.hms.common.valueobject.PatientId;
import com.hms.hospital.patientcase.controller.request.CreatePatientCaseRequestDTO;
import com.hms.hospital.patientcase.entity.PatientCase;
import com.hms.hospital.patientcase.services.PatientCaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/patient-case")
public class PatientCaseController {
	private final PatientCaseService patientCaseService;

	@GetMapping("/all")
	public ResponseEntity<CustomPage<PatientCase>> getAllPatientCases(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(CustomPage.of(
				patientCaseService.getAllPatientCases(page, size)
		));
	}

	@PostMapping("/create")
	public ResponseEntity<Boolean> createPatientCase(
			@RequestBody @Valid CreatePatientCaseRequestDTO patientCaseRequestDTO) {
		PatientCase patientCase = PatientCase.builder()
				.patientId(PatientId.of(patientCaseRequestDTO.patientId()))
				.name(patientCaseRequestDTO.name())
				.status(PatientCaseStatus.PENDING)
				.build();
		patientCaseService.savePatientCase(patientCase, patientCaseRequestDTO.hospitalId());
		return ResponseEntity.ok(true);

	}
}
