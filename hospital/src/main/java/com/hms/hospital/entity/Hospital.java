package com.hms.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hms.common.keycloak.valueobject.KeycloakGroupId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Hospital {
	private Long id;
	@JsonIgnore
	private KeycloakGroupId keycloakGroupId;
	private String name;
	private String address;
	private String phone;
}
