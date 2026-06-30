package com.hms.doctor.repositories.postgres;

import com.hms.doctor.entity.Doctor;
import com.hms.doctor.model.DoctorModel;
import com.hms.doctor.repositories.DoctorRepository;
import com.hms.doctor.repositories.JpaDoctorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlDoctorRepository implements DoctorRepository {
	private final JpaDoctorRepository jpaDoctorRepository;
	private final ModelMapper modelMapper;

	@Override
	public void createDoctor(Doctor doctor) {
		jpaDoctorRepository.save(modelMapper.map(doctor, DoctorModel.class));
	}

	@Override
	public Page<Doctor> getAllDoctors(int page, int size) {
		return jpaDoctorRepository.findAll(PageRequest.of(page, size))
				.map(dm -> modelMapper.map(dm, Doctor.class));
	}
}
