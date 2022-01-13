package com.kakao.cafe.dao;

import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(UserDao.class);

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserVo> findAllUser() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public UserVo filterUserById(String userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
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
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );
    }
}
