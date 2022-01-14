package com.kakao.cafe.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class SpringJdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO user (email, username, password, type, display_name) VALUES (?, ?, ?, ?, ?)",
                            user.getEmail(), user.getUsername(), user.getPassword(), "type", "display_name");
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
        return (rs, rowNum) -> new User(rs.getString("username"), rs.getString("password"), rs.getString("email"));
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE user SET email = ?, username = ?, password = ?, type = ?, display_name = ? WHERE id = ?;",
                            user.getEmail(), user.getUsername(), user.getPassword(), "type", "display_name");
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }
}
