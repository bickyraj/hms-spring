package com.hms.user.services;

import com.hms.common.keycloak.KeycloakService;
import com.hms.common.keycloak.entity.KeycloakUser;
import com.hms.common.keycloak.valueobject.KeycloakUserId;
import com.hms.user.entity.User;
import com.hms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final KeycloakService keycloakService;

	@Transactional
	public void createUser(User user) {
		KeycloakUser keycloakUser = KeycloakUser.builder()
				.username(user.getUsername())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.build();
		String keycloakId = keycloakService.createUser(keycloakUser);
		user.setKeycloakUserId(KeycloakUserId.of(keycloakId));
		userRepository.createUser(user);
	}

	public Page<User> getAllUsers(int page, int size) {
		return userRepository.getAllUsers(page, size);
	}
}
