package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class H2UserRepository implements UserRepository {

	private final JdbcTemplate jdbcTemplate;
	private int maxId;

	@Override
	public void save(User user) {
		jdbcTemplate.update(
				"INSERT INTO USER VALUES (?, ?, ?, ?, ?)",
				maxId++, user.getAccId(), user.getAccPw(), user.getName(), user.getEmail()
		);
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query(
				"SELECT * FROM USER", Mapper);
	}

	@Override
	public User findById(int id) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM USER WHERE id = ?", Mapper, id);
	}

	public User findByAccId(String accId) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM USER WHERE accid = ?", Mapper, accId);
	}

	public User findByName(String name) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM USER WHERE name = ?", Mapper, name);
	}

	@Override
	public void update(User user) {
		jdbcTemplate.update(
				"UPDATE USER SET ACCPW = ?, NAME = ?, EMAIL = ? WHERE id = ?",
				user.getAccPw(), user.getName(), user.getEmail(), user.getId()
		);
	}

	private RowMapper<User> Mapper = (rs, rowNum) -> {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setAccId(rs.getString("ACCID"));
		user.setAccPw(rs.getString("ACCPW"));
		user.setName(rs.getString("NAME"));
		user.setEmail(rs.getString("EMAIL"));
		return user;
	};
}
