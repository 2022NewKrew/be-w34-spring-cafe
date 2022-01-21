package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserRequestDTO;
import com.kakao.cafe.dto.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository{
    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserRequestDTO user) {
        String sql = "INSERT INTO USERS (userId, password, name, email, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public void update(UserUpdateDTO user, Long id) {
        logger.info("update Repository {} {} {} {} {}", user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), id);
        String sql = "UPDATE USERS SET name = ?, password = ?, email = ?, updatedAt = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getEmail(), LocalDateTime.now(), id);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        logger.info("findByUserId Repository {}", userId);
        String sql = "SELECT * FROM USERS WHERE userId = ?";
        return jdbcTemplate.query(sql, this::userRowMapper, userId)
                .stream()
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, this::userRowMapper);
    }

    private User userRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .userId(rs.getString("userId"))
                .password(rs.getString("password"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updatedAt").toLocalDateTime())
                .build();
    }
}
