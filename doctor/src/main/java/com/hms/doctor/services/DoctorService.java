package com.hms.doctor.services;

import com.hms.common.keycloak.KeycloakService;
import com.hms.common.keycloak.entity.KeycloakUser;
import com.hms.common.keycloak.exceptions.KeycloakException;
import com.hms.doctor.entity.Doctor;
import com.hms.doctor.repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

	private final DoctorRepository doctorRepository;
	private final KeycloakService keycloakService;

	public void createDoctor(Doctor doctor) {
		KeycloakUser keycloakUser = KeycloakUser.builder()
				.email(doctor.getEmail())
				.firstName(doctor.getFirstName().concat(" ").concat(doctor.getMiddleName()))
				.lastName(doctor.getLastName())
				.username(doctor.getUsername())
				.build();
		String keycloakId = keycloakService
				.createUser(keycloakUser);
		doctor.setKeycloakId(keycloakId);
		doctorRepository.createDoctor(doctor);
	}
}
