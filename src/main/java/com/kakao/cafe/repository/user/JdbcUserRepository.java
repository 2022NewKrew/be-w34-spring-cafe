package com.kakao.cafe.repository.user;

import com.kakao.cafe.model.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<User> userRowMapper = userRowMapper();

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        final String sql = "INSERT INTO USERS (userId, password, name, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail());
    }

    @Override
    public List<User> findAll() {
        final String sql = "SELECT id, userId, password, name, email, createdAt FROM USERS";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Optional<User> findById(UUID id) {
        final String sql = "SELECT id, userId, password, name, email, createdAt FROM USERS WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, id.toString()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        final String sql = "SELECT id, userId, password, name, email, createdAt FROM USERS WHERE userId = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper, userId));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private static RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User.Builder(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email"))
                .id(UUID.fromString(rs.getString("id")))
                .createdAt(rs.getObject("createdAt", LocalDateTime.class))
                .build();
    }
}
