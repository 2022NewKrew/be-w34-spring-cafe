package com.kakao.cafe.user;

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
                    "INSERT INTO user (email, username, password, type, display_name) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, "type");
            ps.setString(5, "display_name");
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
        return (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
                                        rs.getString("email"));
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE user SET email = ?, username = ?, password = ?, type = ?, display_name = ? WHERE id = ?;",
                user.getEmail(), user.getUsername(), user.getPassword(), "type", "display_name");
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }
}
