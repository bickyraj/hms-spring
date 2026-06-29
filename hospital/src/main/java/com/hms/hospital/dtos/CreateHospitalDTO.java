package com.hms.hospital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateHospitalDTO {
	private final String name;
	private final String phone;
	private final String address;
}
