package com.kakao.cafe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private static final String INSERT_VALUES = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM USERS WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM USERS";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(User user) throws SQLException {
        jdbcTemplate.update(INSERT_VALUES, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public List<User> getUserList() throws SQLException {
        return jdbcTemplate.query(
                SELECT_ALL,
                new UserMapper()
        );
    }

    public User findUserWithId(String id) throws SQLException {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID,
                new UserMapper(),
                id
        );
    }

    public static class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int count) throws SQLException {
            return new User(
                    rs.getString("ID"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getString("EMAIL")
            );
        }
    }
}
