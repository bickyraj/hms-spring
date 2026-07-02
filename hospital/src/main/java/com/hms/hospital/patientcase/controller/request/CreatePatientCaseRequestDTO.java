package com.hms.hospital.patientcase.controller.request;

public record CreatePatientCaseRequestDTO(
		String patientId,
		String name,
		Long hospitalId
) {}
