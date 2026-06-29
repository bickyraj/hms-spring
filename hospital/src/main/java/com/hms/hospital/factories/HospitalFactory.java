package com.hms.hospital.factories;

import com.hms.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;
import com.hms.hospital.repositories.HospitalRepository;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalFactory {
	private final HospitalRepository hospitalRepository;

	public void createHospital(Hospital hospital) {
		hospitalRepository.createHospital(hospital);
	}
}
