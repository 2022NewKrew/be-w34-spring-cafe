package com.kakao.cafe.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO `USER` (userId, password, name, email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());


    }

    @Override
    public User update(String userId, User user) {
        String sql = "UPDATE `USER` SET password=?, name=?, email=? WHERE userId=?";
        jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), userId);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        String sql = "SELECT userId, password, name, email FROM `USER` WHERE userId=?";
        try {
            User user = jdbcTemplate.queryForObject(sql, getUserRowMapper(), userId);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT userId, password, name, email FROM `USER`";
        return jdbcTemplate.query(sql, getUserRowMapper());
    }

    private RowMapper<User> getUserRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getString("userId"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));

            return user;
        };
    }
}
