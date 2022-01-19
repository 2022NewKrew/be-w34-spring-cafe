package com.kakao.cafe.user.repo;

import com.kakao.cafe.user.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper mapper;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, UserRowMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public long save(User target) {
        beforeSave(target);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO `USER`(CREATED_AT, UPDATED_AT, USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setObject(1, target.getCreatedAt());
                    ps.setObject(2, target.getUpdatedAt());
                    ps.setString(3, target.getUserId());
                    ps.setString(4, target.getHashedPassword());
                    ps.setString(5, target.getName());
                    ps.setString(6, target.getEmail());
                    return ps;
                },
                keyHolder
        );
        if (keyHolder.getKey() == null) {
            throw new IllegalStateException("If approached here, there is a database problem.");
        }
        return keyHolder.getKey().longValue();
    }

    @Override
    public User fetch(long id) {
        String query = "SELECT * FROM `USER` WHERE ID = ?";
        return jdbcTemplate.queryForObject(query, mapper, id);
    }

    @Override
    public List<User> fetchAll() {
        String query = "SELECT * FROM `USER`";
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public int getUserCount() {
        String query = "SELECT COUNT(*) FROM `USER`";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Optional<User> fetchByUserId(String userId) {
        String query = "SELECT * FROM `USER` WHERE USER_ID=?";
        User user = jdbcTemplate.queryForObject(query, mapper, userId);
        return Optional.ofNullable(user);
    }
}
