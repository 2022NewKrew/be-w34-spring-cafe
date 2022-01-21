package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.exception.DuplicateUserException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, RowMapper<User> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(User user) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO users (username, password, name, email) VALUES (?, ?, ?, ?)",
                    user.getUserName().getValue(),
                    user.getPassword().getValue(),
                    user.getName().getValue(),
                    user.getEmail().getValue());
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserException("해당 아이디를 사용중인 사용자가 이미 존재합니다.");
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT " +
                "BIN_TO_UUID(users_id) as users_id, " +
                "username, " +
                "password, " +
                "name, " +
                "email FROM users", rowMapper);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT " +
                    "BIN_TO_UUID(users_id) as users_id, " +
                    "username, " +
                    "password, " +
                    "name, " +
                    "email FROM users " +
                    "WHERE users_id = UUID_TO_BIN(?)", rowMapper, id.toString()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET name = ?, email = ? "
                        + "WHERE users_id = UUID_TO_BIN(?)",
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getId().toString());
    }

    @Override
    public Optional<User> findUserByUserNameAndPassword(UserName userName, Password password) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT " +
                    "BIN_TO_UUID(users_id) as users_id, " +
                    "username, " +
                    "password, " +
                    "name, " +
                    "email FROM users " +
                    "WHERE username = ? AND password = ?", rowMapper, userName.getValue(), password.getValue()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}
