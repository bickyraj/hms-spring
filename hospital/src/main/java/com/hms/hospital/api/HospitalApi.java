package com.hms.hospital.api;

import com.hms.hospital.api.dtos.CreateHospitalApiRequestDTO;
import com.hms.hospital.entity.Hospital;
import com.hms.hospital.services.HospitalService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalApi {

	private final HospitalService hospitalService;

	public boolean createHospital(CreateHospitalApiRequestDTO createHospitalDTO) {
		Hospital hospital = Hospital.builder()
				.name(createHospitalDTO.getName())
				.address(createHospitalDTO.getAddress())
				.phone(createHospitalDTO.getPhone())
				.build();
		hospitalService.createHospital(hospital);
		return true;
	}
}
