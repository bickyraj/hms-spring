package com.hms.hospital.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private String middleName;
}
