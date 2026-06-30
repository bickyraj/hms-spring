package com.hms.user.repositories;

import com.hms.common.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserModel, Long> {
}
