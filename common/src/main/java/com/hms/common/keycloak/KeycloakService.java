package com.hms.common.keycloak;

import java.util.List;

import com.hms.common.keycloak.configurations.KeycloakProperties;
import com.hms.common.keycloak.entity.KeycloakUser;
import com.hms.common.keycloak.exceptions.KeycloakException;
import com.hms.common.keycloak.valueobject.KeycloakGroupId;
import com.hms.common.keycloak.valueobject.KeycloakUserId;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {
	private final Keycloak keycloak;
	private final KeycloakProperties keycloakProperties;

	public KeycloakGroupId createGroup(String name) {
		GroupRepresentation groupRepresentation = new GroupRepresentation();
		groupRepresentation.setName(name);
		Response response = keycloak.realm(keycloakProperties.realm())
				.groups()
				.add(groupRepresentation);
		return KeycloakGroupId.of(CreatedResponseUtil.getCreatedId(response));
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

	public void addUsersToGroup(List<KeycloakUserId> userIds, KeycloakGroupId groupId, String role) {
		RealmResource realmResource = keycloak.realm(keycloakProperties.realm());

		for (KeycloakUserId userId : userIds) {
			try {
				// 1. Add user to the group
				UserResource userResource = realmResource.users().get(userId.getValue());
				userResource.joinGroup(groupId.getValue());

				// 2. Assign the role to the user (realm-level role example)
				RoleRepresentation roleRepresentation = realmResource.roles()
						.get(role.toLowerCase())
						.toRepresentation();

				userResource.roles()
						.realmLevel()
						.add(List.of(roleRepresentation));

			} catch (NotFoundException e) {
				// user or group or role doesn't exist in Keycloak
				throw new EntityNotFoundException("Failed to add user to group: " + userId);
			} catch (Exception e) {
				throw new KeycloakException("Failed to add user to group", e);
			}
		}
	}
}
