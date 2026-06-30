package com.hms.doctor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

	private Long id;
	private String username;
	private String firstName;
	@JsonIgnore
	private String keycloakId;
	private String middleName;
	private String lastName;
	private String email;
	private String address;
	private String phone;
}
