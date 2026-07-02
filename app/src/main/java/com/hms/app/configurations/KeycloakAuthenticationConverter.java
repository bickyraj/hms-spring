package com.hms.app.configurations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Value("${keycloak.client-id}")
	private String clientId;

	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");

		if (realmAccess == null || realmAccess.isEmpty()) {
			return authorities;
		}

		@SuppressWarnings("unchecked")
		List<String> roles = (List<String>) realmAccess.get("roles");

		if (roles == null) {
			return authorities;
		}

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
		}

		return authorities;
	}
}
