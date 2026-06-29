package com.hms.hospital.services;

import java.util.Set;

import com.hms.common.keycloak.KeycloakService;
import com.hms.common.keycloak.valueobject.KeycloakGroupID;
import com.hms.hospital.dtos.CreateHospitalDTO;
import com.hms.hospital.entity.Hospital;
import com.hms.hospital.factories.HospitalFactory;
import com.hms.hospital.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {

	private final KeycloakService keycloakService;
	private final HospitalFactory hospitalFactory;
	private final HospitalRepository hospitalRepository;
	private final ModelMapper modelMapper;

	public void createHospital(Hospital hospital) {
		KeycloakGroupID id = keycloakService.createGroup(hospital.getName());
		hospital.setKeycloakGroupId(id);
		hospitalFactory.createHospital(hospital);
	}

	public Page<Hospital> getAllHospitals(int page, int size) {
		return hospitalRepository.getAll(page, size);
	}
}
