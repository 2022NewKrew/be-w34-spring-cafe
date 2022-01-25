package com.kakao.cafe.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        userMapper = (resultSet, rowNumber) -> new User(
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4)
        );
    }

    public void save(User user) {
        String query = "INSERT INTO Users (userId, userPassword, userName, email) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(query, user.getUserId(), user.getPassWord(), user.getUserName(), user.getEmail());
    }

    public List<User> findAll() {
        String query = "SELECT * FROM Users";

        return jdbcTemplate.query(query, userMapper);
    }

    public User findById(String userId) {
        String query = "SELECT userId, userPassword, userName, email FROM Users WHERE userId = ?";

        return jdbcTemplate.queryForObject(query, userMapper, userId);
    }
}
