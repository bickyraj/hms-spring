package com.hms.hospital.repositories;

import java.awt.print.Pageable;
import java.util.Set;

import com.hms.hospital.entity.Hospital;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository {

	void createHospital(Hospital hospital);
	Page<Hospital> getAll(int page, int size);
}
