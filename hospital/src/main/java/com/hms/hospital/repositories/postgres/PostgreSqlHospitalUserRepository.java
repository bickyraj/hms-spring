package com.hms.hospital.repositories.postgres;

import java.util.List;

import com.hms.common.keycloak.valueobject.KeycloakUserId;
import com.hms.common.model.HospitalModel;
import com.hms.common.model.UserModel;
import com.hms.hospital.entity.User;
import com.hms.hospital.repositories.JpaHospitalRepository;
import com.hms.hospital.repositories.JpaHospitalUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlHospitalUserRepository {
	private final JpaHospitalUserRepository jpaHospitalUserRepository;
	private final ModelMapper modelMapper;
	private final JpaHospitalRepository jpaHospitalRepository;

	public Page<User> getUsersByHospitalId(Long hospitalId) {
		return jpaHospitalUserRepository.findAllByHospitalId(hospitalId, PageRequest.of(0, 10))
				.map(userModel -> modelMapper.map(userModel, User.class));
	}

	public List<User> getUsersNotInHospital(Long hospitalId) {
		return jpaHospitalUserRepository.findAllNotInHospital(hospitalId)
				.stream()
				.map(userModel -> modelMapper.map(userModel, User.class))
				.toList();
	}

	public List<User> getAllUsersByIds(List<Long> userIds) {
		return jpaHospitalUserRepository.findAllById(userIds)
				.stream()
				.map(userModel -> {
					User user = modelMapper.map(userModel, User.class);
					user.setKeycloakUserId(KeycloakUserId.of(userModel.getKeycloakId()));
					return user;
				})
				.toList();
	}

	public void addUsersToHospital(List<Long> userIds, Long hospitalId) {
		List<UserModel> userModels = jpaHospitalUserRepository.findAllById(userIds);
		HospitalModel hospitalModel = jpaHospitalRepository
				.findById(hospitalId)
				.orElseThrow(() -> new IllegalArgumentException("hospital not found"));
		userModels.forEach(userModel -> userModel.getHospitals().add(hospitalModel));
		jpaHospitalUserRepository.saveAll(userModels);
	}
}
