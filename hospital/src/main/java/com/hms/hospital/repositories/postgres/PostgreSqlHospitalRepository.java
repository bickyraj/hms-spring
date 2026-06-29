package com.hms.hospital.repositories.postgres;

import com.hms.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;
import com.hms.hospital.model.HospitalModel;
import org.modelmapper.ModelMapper;
import com.hms.hospital.repositories.HospitalRepository;
import com.hms.hospital.repositories.JpaHospitalRepository;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlHospitalRepository implements HospitalRepository {
	private final JpaHospitalRepository jpaHospitalRepository;
	private final ModelMapper modelMapper;

	@Override
	public void createHospital(Hospital hospital) {
		HospitalModel hospitalModel = modelMapper.map(hospital, HospitalModel.class);
		hospitalModel.setKeycloakGroupId(hospital.getKeycloakGroupId().getValue());
		jpaHospitalRepository.save(hospitalModel);
	}
}
