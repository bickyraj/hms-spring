package com.hms.doctor.repositories;

import com.hms.common.model.DoctorModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDoctorRepository extends JpaRepository<DoctorModel, Long> {
}
