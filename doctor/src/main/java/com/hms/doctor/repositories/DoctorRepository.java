package com.hms.doctor.repositories;

import com.hms.doctor.entity.Doctor;

import org.springframework.data.domain.Page;

public interface DoctorRepository {

	void createDoctor(Doctor doctor);
	Page<Doctor> getAllDoctors(int page, int size);
}
