package com.hms.user.repositories.postgres;

import com.hms.common.model.UserModel;
import com.hms.user.entity.User;
import com.hms.user.repositories.JpaUserRepository;
import com.hms.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlUserRepository implements UserRepository {
	private final JpaUserRepository jpaUserRepository;
	private final ModelMapper modelMapper;

	@Override
	public void createUser(User user) {
		UserModel userModel = modelMapper.map(user, UserModel.class);
		userModel.setKeycloakId(user.getKeycloakUserId().getValue());
		jpaUserRepository.save(userModel);
	}

	@Override
	public Page<User> getAllUsers(int page, int size) {
		return jpaUserRepository.findAll(PageRequest.of(page, size))
				.map(um -> modelMapper.map(um, User.class));
	}
}
