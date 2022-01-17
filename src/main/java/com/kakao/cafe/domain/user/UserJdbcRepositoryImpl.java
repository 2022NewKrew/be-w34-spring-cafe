package com.kakao.cafe.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserJdbcRepositoryImpl implements UserRepository {
    private final JdbcTemplate template;

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users(email, nickname, password, createdAt) VALUES(?, ?, ?, ?)";
        template.update(sql, user.getEmail(), user.getNickname(), user.getPassword(), user.getCreatedAt());
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return template.query(sql, userRowMapper());
    }

    @Override
    public Optional<User> findById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return template.query(sql, userRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return template.query(sql, userRowMapper(), email).stream().findFirst();
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET email = ?, nickname = ?, password = ?, updatedAt = ? WHERE id = ?";
        template.update(sql, user.getEmail(), user.getNickname(), user.getPassword(), user.getUpdatedAt(), user.getId());
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM users";
        template.update(sql);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> User.builder()
                    .id(rs.getLong("id"))
                    .email(rs.getString("email"))
                    .nickname(rs.getString("nickname"))
                    .password(rs.getString("password"))
                    .createdAt(getLocalDateTime(rs, "createdAt"))
                    .updatedAt(getLocalDateTime(rs, "updatedAt"))
                    .build();
    }

    private LocalDateTime getLocalDateTime(ResultSet rs, String columnLabel) throws SQLException {
        if (rs.getTimestamp(columnLabel) == null) {
            return null;
        }
        return rs.getTimestamp(columnLabel).toLocalDateTime();
    }
}
