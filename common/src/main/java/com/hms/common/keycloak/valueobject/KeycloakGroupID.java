package com.hms.common.keycloak.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class KeycloakGroupID {
	private String value;
}
