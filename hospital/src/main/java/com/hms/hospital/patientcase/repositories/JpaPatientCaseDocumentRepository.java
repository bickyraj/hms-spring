package com.hms.hospital.patientcase.repositories;

import com.hms.hospital.patientcase.model.PatientCaseDocumentModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPatientCaseDocumentRepository extends JpaRepository<PatientCaseDocumentModel, Long> {
}
