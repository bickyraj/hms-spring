package com.hms.hospital.api.dtos;

import java.util.List;

public record UserIdWithRoleDTO(
		List<Long> userIds,
		String role
) {}
