package com.hms.hospital.patientcase.repositories.postgres;

import com.hms.hospital.patientcase.entity.PatientCase;
import com.hms.hospital.patientcase.model.PatientCaseModel;
import com.hms.hospital.patientcase.repositories.JpaPatientCaseRepository;
import com.hms.hospital.patientcase.repositories.PatientCaseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlPatientCaseRepository implements PatientCaseRepository {
	private final JpaPatientCaseRepository jpaPatientCaseRepository;
	private final ModelMapper modelMapper;

	@Override
	public void upsert(PatientCase patientCase) {
		jpaPatientCaseRepository.save(
				modelMapper.map(patientCase, PatientCaseModel.class)
		);
	}

	@Override
	public Page<PatientCase> getAll(int page, int size) {
		return jpaPatientCaseRepository.findAll(
				PageRequest.of(page, size)
		).map(patientCaseModel -> modelMapper.map(patientCaseModel, PatientCase.class));
	}
}
