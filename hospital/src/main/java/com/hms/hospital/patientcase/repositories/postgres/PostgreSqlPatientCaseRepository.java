package com.hms.hospital.patientcase.repositories.postgres;

import com.hms.common.model.HospitalModel;
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
	public PatientCase upsert(PatientCase patientCase) {
		PatientCaseModel patientCaseModel = modelMapper.map(patientCase, PatientCaseModel.class);
		patientCaseModel.setPatientId(patientCase.getPatientId().getValue());
		HospitalModel hospitalModel = modelMapper.map(patientCase.getHospital(), HospitalModel.class);
		patientCaseModel.setHospital(hospitalModel);
		return modelMapper.map(jpaPatientCaseRepository.save(patientCaseModel), PatientCase.class);
	}

	@Override
	public Page<PatientCase> getAll(int page, int size) {
		return jpaPatientCaseRepository.findAll(
				PageRequest.of(page, size)
		).map(patientCaseModel -> modelMapper.map(patientCaseModel, PatientCase.class));
	}
}
