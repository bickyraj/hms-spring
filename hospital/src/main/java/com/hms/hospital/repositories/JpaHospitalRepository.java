package com.hms.hospital.repositories;

import com.hms.common.model.HospitalModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHospitalRepository extends JpaRepository<HospitalModel, Long> {
}
