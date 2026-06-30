package com.hms.common.keycloak.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class KeycloakGroupId {
	private String value;
}
