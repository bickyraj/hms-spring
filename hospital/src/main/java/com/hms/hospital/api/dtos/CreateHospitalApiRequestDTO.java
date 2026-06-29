package com.hms.hospital.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateHospitalApiRequestDTO {
	private final String name;
	private final String phone;
	private final String address;
}
