package com.hms.hospital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hms.common.keycloak.valueobject.KeycloakGroupID;
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
	private KeycloakGroupID keycloakGroupId;
	private String name;
	private String address;
	private String phone;
}
