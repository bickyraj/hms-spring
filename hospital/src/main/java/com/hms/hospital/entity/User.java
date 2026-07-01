package com.hms.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hms.common.keycloak.valueobject.KeycloakUserId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User {
	private Long id;
	@JsonIgnore
	private KeycloakUserId keycloakUserId;
	private String firstName;
	private String lastName;
	private String middleName;
}
