package com.kakao.cafe.dao;

import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );
    }

    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper());
    }

    public User getUser(String userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE userId = ?", userRowMapper(), userId);
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO users(userId, password, name, email) VALUES(?,?,?,?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void updateUser(User user) {
        jdbcTemplate.update("UPDATE users SET name=?,email=? WHERE userId=? AND password=?",
                user.getName(), user.getEmail(), user.getUserId(), user.getPassword());
    }

}
