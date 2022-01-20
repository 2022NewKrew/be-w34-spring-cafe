package com.kakao.cafe.repository;

import com.kakao.cafe.model.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public List<UserVo> findAllUser() {
        String sql = "SELECT id, user_id, password, name, email FROM users";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public UserVo filterUserById(String userId) {
        try {
            String sql = "SELECT id, user_id, password, name, email FROM users WHERE user_id = ?";
            return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createUser(UserVo user) {
        String sql = "INSERT INTO users (user_id, password, name, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void updateUser(UserVo user) {
        String sql = "UPDATE users SET password = ?, name = ?, email = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    private RowMapper<UserVo> userRowMapper() {
        return (rs, rowNum) -> new UserVo(
                rs.getInt("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );
    }
}
