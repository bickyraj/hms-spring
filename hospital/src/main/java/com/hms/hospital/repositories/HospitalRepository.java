package com.hms.hospital.repositories;

import com.hms.hospital.entity.Hospital;

import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository {

	void createHospital(Hospital hospital);
}
