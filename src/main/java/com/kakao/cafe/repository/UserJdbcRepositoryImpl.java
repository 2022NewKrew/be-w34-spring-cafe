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
	private final static String SAVE_QUERY = "INSERT INTO `user`(user_id, password, name, email) VALUES(?, ?, ?, ?)";
	private final static String FIND_ALL_QUERY = "SELECT * FROM `user`";
	private final static String FIND_BY_ID_QUERY = "SELECT * FROM `user` WHERE id = ?";
	private final static String UPDATE_QUERY = "UPDATE `user` SET password = ?, name = ?, email = ? WHERE id = ?";

	private final JdbcTemplate jdbcTemplate;
	private final UserRowMapper userRowMapper;

	@Override
	public void save(User user) {
		jdbcTemplate.update(SAVE_QUERY, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	@Override
	public List<User> findAll() {
		List<User> userList = jdbcTemplate.query(FIND_ALL_QUERY, userRowMapper);

		return userList;
	}

	@Override
	public User findById(int id) {
		User user = jdbcTemplate.queryForObject(FIND_BY_ID_QUERY, userRowMapper, id);

		return user;
	}

	@Override
	public void update(int id, User user) {
		jdbcTemplate.update(UPDATE_QUERY, user.getPassword(), user.getName(), user.getEmail(), id);
	}
}
