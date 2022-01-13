package com.kakao.cafe.dao;

import com.kakao.cafe.vo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        return jdbcTemplate.query("select * from article",
                (rs, rowNum) -> new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                )
        );
    }

    public User getUser(String userId) {
        return jdbcTemplate.queryForObject("select * from user where userId = ?",
                (rs, rowNum) -> new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                ),
                userId
        );
    }

    public void addUser(User user) {
        jdbcTemplate.update("insert into user(userId, password, name, email) values(?,?,?,?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void updateUser(User user) {
        jdbcTemplate.update("update user set name=?,email=? where userId=? and password=?",
                user.getName(), user.getEmail(), user.getUserId(), user.getPassword());
    }

}
