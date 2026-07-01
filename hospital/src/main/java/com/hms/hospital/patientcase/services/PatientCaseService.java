package com.hms.hospital.patientcase.services;

import com.hms.hospital.patientcase.entity.PatientCase;
import com.hms.hospital.patientcase.repositories.PatientCaseRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientCaseService {
	private final PatientCaseRepository patientCaseRepository;

	public void savePatientCase(PatientCase patientCase) {
		patientCaseRepository.upsert(patientCase);
	}

	public Page<PatientCase> getAllPatientCases(int page, int size) {
		return patientCaseRepository.getAll(page, size);
	}
}
