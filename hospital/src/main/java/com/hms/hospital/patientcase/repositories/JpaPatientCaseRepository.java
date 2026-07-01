package com.hms.hospital.patientcase.repositories;

import com.hms.hospital.patientcase.model.PatientCaseModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPatientCaseRepository extends JpaRepository<PatientCaseModel, Long> {
}
