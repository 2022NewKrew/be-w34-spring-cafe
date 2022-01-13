package com.kakao.cafe.web.service;

import com.kakao.cafe.UserMapper;
import com.kakao.cafe.domain.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private final JdbcTemplate jdbcTemplate;

    private UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Users> getUsers() {
        String sql = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void addUser(Users user) {
        String sql = "INSERT INTO USERS (USERID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public Users getByUserId(int id) {
        String sql = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE ID=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public void updateUser(int id, Users updateUser, String newPassword) {
        if (!getByUserId(id).getPassword().equals((updateUser.getPassword())))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        if (!newPassword.isBlank())
            updateUser.setPassword(newPassword);
        String sql = "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE ID=?";
        jdbcTemplate.update(sql, updateUser.getPassword(), updateUser.getName(), updateUser.getEmail(), updateUser.getId());
    }

}
