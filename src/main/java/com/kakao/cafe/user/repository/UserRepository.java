package com.kakao.cafe.user.repository;

import java.util.List;
import java.util.Optional;

import com.kakao.cafe.user.entity.User;

public interface UserRepository {
	void save(User user);

	List<User> findAll();

	Optional<User> findById(int id);

	Optional<User> findByUserId(String userId);

	void update(int id, User user);
}
