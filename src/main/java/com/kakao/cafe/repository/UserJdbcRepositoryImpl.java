package com.kakao.cafe.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.mapper.UserRowMapper;

import lombok.RequiredArgsConstructor;

@Primary
@Repository
@RequiredArgsConstructor
public class UserJdbcRepositoryImpl implements UserRepository {
	private final JdbcTemplate jdbcTemplate;
	private final UserRowMapper userRowMapper;

	@Override
	public void save(User user) {
		String sql = "INSERT INTO `user`(user_id, password, name, email) VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM `user`";
		List<User> userList = jdbcTemplate.query(sql, userRowMapper);

		return userList;
	}

	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM `user` WHERE id = ?";
		User user = jdbcTemplate.queryForObject(sql, userRowMapper, id);

		return user;
	}

	@Override
	public void update(int id, User user) {
		String sql = "UPDATE `user` SET password = ?, name = ?, email = ? WHERE id = ?";
		jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), id);
	}
}
