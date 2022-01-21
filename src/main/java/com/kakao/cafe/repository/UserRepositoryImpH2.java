package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserRepositoryImpH2 implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpH2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createUser(User user) {
        jdbcTemplate.update(
            "INSERT INTO USERS (USER_ID, PASSWORD, NAME, EMAIL) VALUES ( ?, ?, ?, ? )",
            user.getUsername(), user.getPassword(), user.getName(), user.getEmail()
        );
    }

    @Override
    public boolean isUsernameUsed(String username) {
        List<User> result = jdbcTemplate.query(
            "SELECT * FROM USERS WHERE USER_ID=?",
            userRowMapper(), username
        );
        return !result.isEmpty();
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(
            "SELECT * FROM USERS",
            userRowMapper()
        );
    }

    @Override
    public User findByUsername(String username) {
        List<User> result = jdbcTemplate.query(
            "SELECT * FROM USERS WHERE USER_ID=?",
            userRowMapper(), username
        );
        return result.get(0);
    }

    @Override
    public User updateUser(User user) {
        jdbcTemplate.update(
            "UPDATE USERS SET NAME=?, EMAIL=? WHERE USER_ID=?",
            user.getName(), user.getEmail(), user.getUsername()
        );
        return user;
    }

    @Override
    public String findIdByUsername(String username) {
        List<User> result = jdbcTemplate.query(
            "SELECT * FROM USERS WHERE USER_ID=?",
            userRowMapper(), username
        );
        return result.get(0).getId();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, count) -> new User(
            rs.getString("ID"),
            rs.getString("USER_ID"),
            rs.getString("PASSWORD"),
            rs.getString("NAME"),
            rs.getString("EMAIL")
        );
    }
}
