package com.kakao.cafe.repository;

import com.kakao.cafe.dto.UserRequestDto;
import com.kakao.cafe.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
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
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserRequestDto user) {
        String sql = "INSERT INTO USERS (userId, password, name, email, createdAt) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), LocalDateTime.now());
    }

    @Override
    public void update(Long id, UserRequestDto user) {
        String sql = "UPDATE USERS SET name = ?, password = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getEmail(), id);
    }

    @Override
    public Optional<UserResponseDto> findByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE userId = ?";
        return jdbcTemplate.query(sql, this::userRowMapper, userId)
                .stream()
                .findFirst();
    }

    @Override
    public List<UserResponseDto> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, this::userRowMapper);
    }

    private UserResponseDto userRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return UserResponseDto.of(rs.getLong("id"),
                rs.getString("userId"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getTimestamp("createdAt").toLocalDateTime());
    }
}
