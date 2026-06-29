package com.hms.common.exception;

import com.hms.common.ApiResponse;
import jakarta.validation.ValidationException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse> handleKeycloakException(ValidationException ex) {
		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}
}
