package com.kakao.cafe.repository;

import java.util.List;

import com.kakao.cafe.domain.User;

public interface UserRepository {
	void save(User user);

	List<User> findAll();

	User findById(int id);
}
