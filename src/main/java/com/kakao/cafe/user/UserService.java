package com.kakao.cafe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<Users> userList = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Users> getUserList() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void createUser(Users user) {
        String sql = "INSERT INTO USERS (USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public Users getUserById(Long id) {
        String sql = "SELECT * FROM USERS WHERE ID=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public Users getUserByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE USER_Id=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), userId);
    }
}
