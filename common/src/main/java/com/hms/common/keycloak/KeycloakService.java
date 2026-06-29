package com.hms.common.keycloak;

import com.hms.common.keycloak.configurations.KeycloakProperties;
import com.hms.common.keycloak.valueobject.KeycloakGroupID;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {
	private final Keycloak keycloak;
	private final KeycloakProperties keycloakProperties;

	public KeycloakGroupID createGroup(String name) {
		GroupRepresentation groupRepresentation = new GroupRepresentation();
		groupRepresentation.setName(name);
		Response response = keycloak.realm(keycloakProperties.realm())
				.groups()
				.add(groupRepresentation);
		return KeycloakGroupID.of(CreatedResponseUtil.getCreatedId(response));
	}
}
