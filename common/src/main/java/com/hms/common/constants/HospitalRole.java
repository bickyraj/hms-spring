package com.hms.common.constants;

public enum HospitalRole {
	DOCTOR("DOCTOR"),
	NURSE("NURSE"),
	ADMIN("ADMIN"),
	RECEPTIONIST("RECEPTIONIST");

	private final String role;

	HospitalRole(String role) {
		this.role = role;
	}

	public String getValue() {
		return role;
	}
}
