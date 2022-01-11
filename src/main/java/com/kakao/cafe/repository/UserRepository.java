package com.kakao.cafe.repository;

import java.util.List;

import com.kakao.cafe.entity.User;

public interface UserRepository {
	void save(User user);

	List<User> findAll();

	User findById(int id);
}
