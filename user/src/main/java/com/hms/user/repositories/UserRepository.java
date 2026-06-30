package com.hms.user.repositories;

import com.hms.user.entity.User;

import org.springframework.data.domain.Page;

public interface UserRepository {

	void createUser(User user);
	Page<User> listUser(int page, int size);
}
