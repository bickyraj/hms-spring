package com.hms.user.entity;

import com.hms.common.keycloak.valueobject.KeycloakUserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
	private Long id;
	private KeycloakUserId keycloakUserId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String username;
}
