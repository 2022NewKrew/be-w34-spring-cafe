package com.kakao.cafe.web;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepository {
    private static final String INSERT_VALUES = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM USERS WHERE ID = ?";
    private static final String SELECT_BY_ID_AND_PASSWORD = "SELECT * FROM USERS WHERE ID = ? AND PASSWORD = ?";
    private static final String SELECT_ALL = "SELECT * FROM USERS";
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) throws DataAccessException, IllegalArgumentException {
        if(user.getId().equals("form")) {
            throw new IllegalArgumentException();
        }
        jdbcTemplate.update(INSERT_VALUES, user.getId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public List<User> getUserList() throws DataAccessException {
        return jdbcTemplate.query(
                SELECT_ALL,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    public User findUserWithId(String id) throws DataAccessException {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID,
                new BeanPropertyRowMapper<>(User.class),
                id
        );
    }

    public User findUserWithIdAndPassword(String id, String password) throws DataAccessException {
        return jdbcTemplate.queryForObject(
                SELECT_BY_ID_AND_PASSWORD,
                new BeanPropertyRowMapper<>(User.class),
                id,
                password
        );
    }
}
