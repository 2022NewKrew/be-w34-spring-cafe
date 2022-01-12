package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Primary
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
        jdbcTemplate.update("INSERT INTO users (username, password, name, email) VALUES (?, ?, ?, ?)",
                user.getUserName().getValue(),
                user.getPassword().getValue(),
                user.getName().getValue(),
                user.getEmail().getValue());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    @Override
    public Optional<User> findUserByName(UserName id) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", rowMapper, id.getValue());
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE users_id = ?", rowMapper, id.toString());
        return Optional.ofNullable(user);
    }
}
