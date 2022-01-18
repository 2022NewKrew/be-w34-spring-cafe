package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class MemoryUserRepository implements UserRepository {

	private final List<User> users;
	private int maxId = 0;

	@Override
	public void save(User user) {
		user.setId(maxId++);
		users.add(user);
	}

	@Override
	public User findById(int id) {
		return users.stream().filter(user -> user.getId() == id).findAny().orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public void update(User user) {
		User target = findById(user.getId());
		int index = users.indexOf(target);
		users.set(index, user);
	}
}
