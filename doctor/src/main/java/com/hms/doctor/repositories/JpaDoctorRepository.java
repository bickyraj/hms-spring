package com.hms.doctor.repositories;

import com.hms.doctor.model.DoctorModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDoctorRepository extends JpaRepository<DoctorModel, Long> {
}
