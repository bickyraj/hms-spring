package com.hms.doctor.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Doctor {

	private Long id;
	private String firstName;
	private String LastName;
	private String email;
	private String address;
	private String phone;
}
