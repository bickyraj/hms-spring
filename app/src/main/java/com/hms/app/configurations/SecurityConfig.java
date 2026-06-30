package com.hms.app.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(CsrfConfigurer::disable)
				.cors(CorsConfigurer::disable)
//				.oauth2ResourceServer(oauth2 -> {
//					oauth2.jwt(jwtConfigurer -> jwtConfigurer
//							.jwtAuthenticationConverter(jwtAuthenticationConverter()));
//				})
				.authorizeHttpRequests((authorize) -> {
					authorize.anyRequest().permitAll();
				});

		return http.build();
	}

//	@Bean
//	public JwtAuthenticationConverter jwtAuthenticationConverter() {
//		JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//		converter.setJwtGrantedAuthoritiesConverter(new KeycloakAuthenticationConverter());
//		return converter;
//	}
}
