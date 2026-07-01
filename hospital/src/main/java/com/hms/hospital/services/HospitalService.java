package com.hms.hospital.services;

import java.util.List;

import com.hms.common.constants.HospitalRole;
import com.hms.common.keycloak.KeycloakService;
import com.hms.common.keycloak.valueobject.KeycloakGroupId;
import com.hms.common.keycloak.valueobject.KeycloakUserId;
import com.hms.hospital.entity.Hospital;
import com.hms.hospital.entity.User;
import com.hms.hospital.factories.HospitalFactory;
import com.hms.hospital.repositories.HospitalRepository;
import com.hms.hospital.repositories.postgres.PostgreSqlHospitalUserRepository;
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
	private final PostgreSqlHospitalUserRepository postgreSqlHospitalUserRepository;
	private final ModelMapper modelMapper;

	public void createHospital(Hospital hospital) {
		KeycloakGroupId id = keycloakService.createGroup(hospital.getName());
		hospital.setKeycloakGroupId(id);
		hospitalFactory.createHospital(hospital);
	}

	public Page<Hospital> getAllHospitals(int page, int size) {
		return hospitalRepository.getAll(page, size);
	}

	public Hospital getHospitalById(Long id) {
		return hospitalRepository.getById(id).orElseThrow(() ->
				new IllegalArgumentException("hospital not found"));
	}

	public Page<User> getHospitalUsers(Long hospitalId) {
		return postgreSqlHospitalUserRepository.getUsersByHospitalId(hospitalId);
	}

	public List<User> getUsersNotInHospital(Long hospitalId) {
		return postgreSqlHospitalUserRepository.getUsersNotInHospital(hospitalId);
	}

	public void addUsersWithRoleToHospital(List<Long> userIds, Long hospitalId, HospitalRole role) {
		Hospital hospital = getHospitalById(hospitalId);
		List<KeycloakUserId> keycloakUserIds = postgreSqlHospitalUserRepository
				.getAllUsersByIds(userIds)
				.stream()
				.map(User::getKeycloakUserId)
				.toList();
		keycloakService.addUsersToGroup(keycloakUserIds, hospital.getKeycloakGroupId(), role.getValue());
		postgreSqlHospitalUserRepository.addUsersToHospital(userIds, hospitalId);
	}
}
