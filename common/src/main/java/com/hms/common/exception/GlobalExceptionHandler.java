package com.hms.common.exception;

import java.util.ArrayList;
import java.util.List;

import com.hms.common.ApiResponse;
import com.hms.common.valueobject.FieldError;
import jakarta.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleValidationErrors(
			MethodArgumentNotValidException ex) {

		List<FieldError> errors = new ArrayList<>();

		ex.getBindingResult().getFieldErrors().forEach(error ->
				errors.add(new FieldError(error.getField(), error.getDefaultMessage()))
		);
		return new ResponseEntity<>(
				ApiResponse.builder()
						.status(false)
						.message("validation failed")
						.validationErrors(errors)
						.build(),
				HttpStatus.BAD_REQUEST
		);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(400).body(
				ApiResponse.builder().message(ex.getMessage()).build()
		);
	}
}
