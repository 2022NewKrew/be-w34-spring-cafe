package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;

import java.util.List;

public interface UserRepository {

	void save(User user);

	List<User> findAll();

	User findById(int id);

	void update(User user);
}
