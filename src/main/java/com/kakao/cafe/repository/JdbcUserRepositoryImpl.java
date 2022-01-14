package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcUserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        try {
            jdbcTemplate.queryForObject("SELECT id FROM USER_PROFILE WHERE user_id = ?", String.class, user.getUserId());
            jdbcTemplate.update("UPDATE USER_PROFILE " +
                    "SET password = ?, name = ?, email = ? " +
                    "WHERE user_id = ?", user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            jdbcTemplate.update("INSERT INTO USER_PROFILE(user_id, password, name, email) " +
                    "VALUES(?, ? ,? ,?)", user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT user_id, password, name, email FROM USER_PROFILE", this::userMapRowWithoutId);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT user_id, password, name, email FROM USER_PROFILE WHERE user_id = ?", this::userMapRowWithoutId, userId));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    private User userMapRowWithoutId(ResultSet resultSet, int rowNum) throws SQLException {
        return new User(resultSet.getString("user_id"), resultSet.getString("password"),
                resultSet.getString("name"), resultSet.getString("email"));
    }
}
