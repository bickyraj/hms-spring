package com.hms.hospital.patientcase;

import com.hms.common.CustomPage;
import com.hms.hospital.patientcase.entity.PatientCase;
import com.hms.hospital.patientcase.services.PatientCaseService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
}
