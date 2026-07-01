package com.hms.hospital.repositories;

import com.hms.common.model.UserModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaHospitalUserRepository extends JpaRepository<UserModel, Long> {
	@Query(value = "SELECT u.* FROM users u " +
			"JOIN user_hospital uh ON u.id = uh.user_id " +
			"WHERE uh.hospital_id = :hospitalId",
			countQuery = "SELECT count(*) FROM users u " +
					"JOIN user_hospital uh ON u.id = uh.user_id " +
					"WHERE uh.hospital_id = :hospitalId",
			nativeQuery = true)
	Page<UserModel> findAllByHospitalId(@Param("hospitalId") Long hospitalId, Pageable pageable);
}
