package com.hms.hospital.controller;

import com.hms.common.ApiResponse;
import com.hms.common.CustomPage;
import com.hms.common.constants.HospitalRole;
import com.hms.hospital.api.dtos.UserIdWithRoleDTO;
import com.hms.hospital.dtos.CreateHospitalDTO;
import com.hms.hospital.entity.Hospital;
import com.hms.hospital.entity.User;
import com.hms.hospital.services.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
@Validated
public class HospitalController {

	private final HospitalService hospitalService;
	private final ModelMapper modelMapper;

	@GetMapping("/list")
	public ResponseEntity<CustomPage<Hospital>> list() {
		return ResponseEntity.ok(CustomPage.of(hospitalService.getAllHospitals(0, 10)));
	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createHospital(@Valid @RequestBody CreateHospitalDTO createHospitalDTO) {
		hospitalService.createHospital(modelMapper.map(createHospitalDTO, Hospital.class));
		return ResponseEntity.ok(ApiResponse.builder()
				.message("hospital created").build());
	}

	@GetMapping("/{hospitalId}")
	public ResponseEntity<ApiResponse> getHospitalDetail(@PathVariable Long hospitalId) {
		return ResponseEntity.ok(ApiResponse.builder()
				.data(hospitalService.getHospitalById(hospitalId))
				.message("hospital retrieved")
				.status(true)
				.build());
	}

	@GetMapping("/{hospitalId}/staffs")
	public ResponseEntity<CustomPage<User>> getHospitalUsers(@PathVariable Long hospitalId) {
		return ResponseEntity.ok(CustomPage.of(hospitalService.getHospitalUsers(hospitalId)));
	}

	@GetMapping("/{hospitalId}/staffs/not-in-hospital")
	public ResponseEntity<ApiResponse> getUsersNotInHospital(@PathVariable Long hospitalId) {
		return ResponseEntity.ok(ApiResponse.builder()
				.data(hospitalService.getUsersNotInHospital(hospitalId))
				.message("users not in hospital retrieved")
				.status(true)
				.build());
	}

	@PostMapping("/{hospitalId}/staffs/add")
	public ResponseEntity<ApiResponse> addUsersWithRoleToHospital(@RequestBody UserIdWithRoleDTO userIdWithRoleDTO,
																  @PathVariable Long hospitalId) {
		hospitalService.addUsersWithRoleToHospital(userIdWithRoleDTO.userIds(),
				hospitalId, HospitalRole.valueOf(userIdWithRoleDTO.role()));
		return ResponseEntity.ok(ApiResponse.builder()
				.message("users added to hospital")
				.status(true)
				.build());

	}
}
