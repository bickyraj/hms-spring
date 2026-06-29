package com.hms.common.keycloak;

import com.hms.common.keycloak.configurations.KeycloakProperties;
import com.hms.common.keycloak.entity.KeycloakUser;
import com.hms.common.keycloak.exceptions.KeycloakException;
import com.hms.common.keycloak.valueobject.KeycloakGroupID;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

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

	public String createUser(KeycloakUser keycloakUser) {
		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setEmailVerified(true);
		userRepresentation.setFirstName(keycloakUser.getFirstName());
		userRepresentation.setLastName(keycloakUser.getLastName());
		userRepresentation.setEmail(keycloakUser.getEmail());
		userRepresentation.setUsername(keycloakUser.getUsername());
		userRepresentation.setEnabled(true);
		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		credentialRepresentation.setTemporary(false);
		credentialRepresentation.setValue("test");

		try (Response response = keycloak
				.realm(keycloakProperties.realm())
				.users()
				.create(userRepresentation)) {
			;
			if (response.getStatus() == 409) {
				throw new ValidationException("email already exists");
			}
			return CreatedResponseUtil.getCreatedId(response);
		}
	}
}
