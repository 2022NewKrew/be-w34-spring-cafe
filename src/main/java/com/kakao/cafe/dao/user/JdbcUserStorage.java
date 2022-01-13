package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcUserStorage implements UserDao {
    JdbcTemplate jdbcTemplate;

    public JdbcUserStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        String query = "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USER_DATA";
        return jdbcTemplate.query(query, (rs, rowNum) -> toUser(rs).orElseThrow(SQLDataException::new));
    }

    @Override
    public void addUser(String userId, String password, String name, String email) {
        String query = "INSERT INTO USER_DATA(USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, userId, password, name, email);
    }

    @Override
    public Optional<User> findUserById(String userId) {
        String query = String.format("SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USER_DATA WHERE USER_ID = '%s'", userId);
        return jdbcTemplate
                .query(query, (rs, rowNum) -> toUser(rs))
                .get(0);
    }

    @Override
    public int getSize() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER_DATA", Integer.class);
    }

    @Override
    public void update(String userId, String name, String email) {
        String query = "UPDATE USER_DATA SET NAME = ?, EMAIL = ? WHERE USER_ID = ?";
        jdbcTemplate.update(query, name, email, userId);
    }

    private Optional<User> toUser(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new User(
                        resultSet.getString("USER_ID"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("NAME"),
                        resultSet.getString("EMAIL")
                )
        );
    }
}
