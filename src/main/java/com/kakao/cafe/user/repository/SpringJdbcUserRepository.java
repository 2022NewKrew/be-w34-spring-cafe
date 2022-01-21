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
                    "INSERT INTO cafe_user (email, username, password, status, display_name) VALUES (?, ?, ?, ?, ?)",
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
        return jdbcTemplate.query("SELECT * FROM cafe_user", userRowMapper());
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM cafe_user WHERE username = ?", userRowMapper(),
                                  username).stream().findAny();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM cafe_user WHERE email = ?", userRowMapper(),
                                  email).stream().findAny();
    }

    @Override
    public Optional<User> getById(Long id) {
        return jdbcTemplate.query("SELECT * FROM cafe_user WHERE id = ?", userRowMapper(), id).stream().findAny();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> User.builder()
                                   .id(rs.getLong("id"))
                                   .username(rs.getString("username"))
                                   .password(rs.getString("password"))
                                   .email(rs.getString("email"))
                                   .displayName(rs.getString("display_name"))
                                   .status(rs.getString("status"))
                                   .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                   .lastModifiedAt(rs.getTimestamp("last_modified_at").toLocalDateTime())
                                   .build();
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE cafe_user SET email = ?, display_name = ? WHERE id = ?;",
                user.getEmail(), user.getDisplayName(), user.getId());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM cafe_user WHERE id = ?", id);
    }
}
