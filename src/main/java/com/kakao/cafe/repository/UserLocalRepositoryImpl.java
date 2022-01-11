package com.kakao.cafe.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kakao.cafe.entity.User;

@Repository
public class UserLocalRepositoryImpl implements UserRepository {
	private static Map<Integer, User> userMap = new HashMap<>();
	private static int sequence = 0;

	@Override
	public void save(User user) {
		user.setId(++sequence);
		userMap.put(user.getId(), user);
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<>(userMap.values());
	}

	@Override
	public User findById(int id) {
		return userMap.get(id);
	}
}
