package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class SpringJdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long add(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO user (email, username, password, status, display_name) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getStatus());
            ps.setString(5, user.getDisplayName());
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKeys().get("id");
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user", userRowMapper());
    }

    @Override
    public Optional<User> get(String username) {
        return jdbcTemplate.query("SELECT * FROM user WHERE username = ?", userRowMapper(),
                                  username).stream().findAny();
    }

    @Override
    public Optional<User> get(Long id) {
        return jdbcTemplate.query("SELECT * FROM user WHERE id = ?", userRowMapper(), id).stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("email"), rs.getString("username"),
                                        rs.getString("password"), rs.getString("status"), rs.getString("display_name"),
                                        rs.getTimestamp("created_at").toLocalDateTime(),
                                        rs.getTimestamp("last_modified_at").toLocalDateTime());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE user SET email = ?, display_name = ? WHERE id = ?;",
                user.getEmail(), user.getDisplayName(), user.getId());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }
}
