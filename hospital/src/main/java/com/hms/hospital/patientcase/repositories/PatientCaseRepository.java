package com.hms.hospital.patientcase.repositories;

import com.hms.hospital.patientcase.entity.PatientCase;
import org.springframework.data.domain.Page;

public interface PatientCaseRepository {

	void upsert(PatientCase patientCase);
	Page<PatientCase> getAll(int page, int size);
}
