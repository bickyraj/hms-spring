package com.hms.hospital.repositories.postgres;

import com.hms.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;
import com.hms.common.model.HospitalModel;
import org.modelmapper.ModelMapper;
import com.hms.hospital.repositories.HospitalRepository;
import com.hms.hospital.repositories.JpaHospitalRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Page<Hospital> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return jpaHospitalRepository.findAll(pageable)
				.map(hm -> modelMapper.map(hm, Hospital.class));
	}
}
