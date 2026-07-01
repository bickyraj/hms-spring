package com.hms.hospital.repositories.postgres;

import java.util.List;

import com.hms.hospital.entity.User;
import com.hms.hospital.repositories.JpaHospitalUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlHospitalUserRepository {
	private final JpaHospitalUserRepository jpaHospitalUserRepository;
	private final ModelMapper modelMapper;

	public List<User> getUsersByHospitalId(Long hospitalId) {
		return jpaHospitalUserRepository.findAllByHospitalId(hospitalId)
				.stream()
				.map(userModel -> modelMapper.map(userModel, User.class))
				.toList();
	}
}
