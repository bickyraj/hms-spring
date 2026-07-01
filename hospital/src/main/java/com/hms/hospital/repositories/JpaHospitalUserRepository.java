package com.hms.hospital.repositories;

import java.util.List;

import com.hms.common.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaHospitalUserRepository extends JpaRepository<UserModel, Long> {
	@Query(value = "SELECT u.* FROM users u " +
			"JOIN user_hospital uh ON u.id = uh.user_id " +
			"WHERE uh.hospital_id = :hospitalId",
			nativeQuery = true)
	List<UserModel> findAllByHospitalId(@Param("hospitalId") Long hospitalId);
}
