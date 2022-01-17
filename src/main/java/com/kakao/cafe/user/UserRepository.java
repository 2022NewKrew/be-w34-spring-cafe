package com.kakao.cafe.user;

import com.kakao.cafe.exception.CustomEmptyDataAccessException;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.Users;
import com.kakao.cafe.user.exception.CustomDuplicateUserException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final RowMapper<User> userRowMapper = getUserMapper();
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        String sql = "INSERT INTO users (user_id, password, name, email) values (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                sql,
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
            );
        } catch (DuplicateKeyException e) {
            throw new CustomDuplicateUserException(e);
        }
    }

    public Users findAll() {
        String sql = "SELECT * FROM users";
        return new Users(jdbcTemplate.query(sql, userRowMapper));
    }

    public User findById(String userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new CustomEmptyDataAccessException(e);
        }
    }

    private RowMapper<User> getUserMapper() {
        return (resultSet, rowNumber) -> new User(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4)
        );
    }
}
