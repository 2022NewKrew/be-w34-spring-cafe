package com.kakao.cafe.web.user;

import com.kakao.cafe.web.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    private UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> readUsers() {
        String sql = "SELECT ID, USERID, EMAIL, NAME, PASSWORD FROM USERS";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void createUser(User user) {
        String sql = "INSERT INTO USERS (USERID, EMAIL, NAME, PASSWORD) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getEmail(), user.getName(), user.getPassword());
    }

    public User findUserById(int id) {
        String sql = "SELECT ID, USERID, EMAIL, NAME, PASSWORD FROM USERS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public User findUserByEmail(String email) {
        String sql = "SELECT ID, USERID, NAME, PASSWORD, EMAIL FROM USERS WHERE EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), email);
    }

    public void updateUser(User updatedUser, int id, String password) {
        if (!updatedUser.getPassword().equals(password))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        String sql = "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE ID = ?";
        jdbcTemplate.update(sql, updatedUser.getName(), updatedUser.getEmail(), updatedUser.getId());
    }
}
