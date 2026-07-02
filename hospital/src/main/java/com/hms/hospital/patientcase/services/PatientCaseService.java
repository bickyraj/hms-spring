package com.hms.hospital.patientcase.services;

import com.hms.hospital.entity.Hospital;
import com.hms.hospital.patientcase.entity.PatientCase;
import com.hms.hospital.patientcase.repositories.PatientCaseRepository;
import com.hms.hospital.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientCaseService {
	private final PatientCaseRepository patientCaseRepository;
	private final HospitalRepository hospitalRepository;
	public void savePatientCase(PatientCase patientCase, Long hospitalId) {
		Hospital hospital = hospitalRepository.getById(hospitalId)
				.orElseThrow(() -> new IllegalArgumentException("Hospital not found with id: " + hospitalId));
		patientCase.setHospital(hospital);
		patientCaseRepository.upsert(patientCase);
	}

	public Page<PatientCase> getAllPatientCases(int page, int size) {
		return patientCaseRepository.getAll(page, size);
	}
}
