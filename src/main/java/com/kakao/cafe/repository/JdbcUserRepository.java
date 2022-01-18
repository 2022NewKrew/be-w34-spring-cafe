package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private static Long idNumber = 0L;

    public JdbcUserRepository(JdbcTemplate dataSource) {
        this.jdbcTemplate = dataSource;
    }

    @Override
    public Long generateId() {
        return ++idNumber;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    @Override
    public User findByUserId(String userId) {
        String sql = "SELECT * FROM users where userId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("해당하는 사용자 ID가 존재하지 않습니다.");
        }
    }

    @Override
    public User findByIDPW(String userId, String password) {
        String sql = "SELECT * FROM users where userID = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper(), userId, password);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("입력한 ID, PW 와 일치하는 사용자가 존재하지 않습니다.");
        }
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
    }
}
