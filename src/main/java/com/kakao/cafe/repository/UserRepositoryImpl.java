package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.repository.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper = new UserMapper();

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM USERS", rowMapper);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE user_id = ?", rowMapper, userId);
        return Optional.ofNullable(user);
    }

    public Long save(User user) {
        if(user.getId() == null) {
            return insertUser(user);
        }
        return updateUser(user);
    }

    private Long insertUser(User user) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO USERS(user_id, password, name, email, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));
            statement.setTimestamp(6, Timestamp.valueOf(user.getUpdatedAt()));
            return statement;
        }, generatedKeyHolder);
        Long id = generatedKeyHolder.getKey().longValue();
        user.setId(id);
        return id;
    }

    private Long updateUser(User user) {
        jdbcTemplate.update("UPDATE USERS SET password = ?, name = ?, email = ?, updated_at = ? WHERE id = ?",
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getUpdatedAt(),
                user.getId());
        return user.getId();
    }
}
