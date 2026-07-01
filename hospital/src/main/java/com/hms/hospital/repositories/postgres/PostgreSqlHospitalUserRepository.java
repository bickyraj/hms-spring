package com.hms.hospital.repositories.postgres;

import java.util.List;

import com.hms.hospital.entity.User;
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

	public Page<User> getUsersByHospitalId(Long hospitalId) {
		return jpaHospitalUserRepository.findAllByHospitalId(hospitalId, PageRequest.of(0, 10))
				.map(userModel -> modelMapper.map(userModel, User.class));
	}
}
