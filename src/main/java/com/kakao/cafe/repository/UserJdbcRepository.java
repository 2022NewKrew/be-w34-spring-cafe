package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
public class UserJdbcRepository implements UserRepository {

    public final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(
                "insert into users(id, userId, password, userName, email) values (?, ?, ?, ?, ?)",
                user.getId(),
                user.getUserId(),
                user.getPassword(),
                user.getUserName(),
                user.getEmail()
        );
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM users ORDER BY id DESC",
                mapper
        );
    }

    @Override
    public Optional<User> findOne(Integer id) {
        return jdbcTemplate.query(
                "select id, userId, password, userName, email from users where id = ?",
                mapper,
                id
        ).stream().findAny();
    }

    public Optional<User> findByUserId(String userId) {
        return jdbcTemplate.query(
                "select id, userId, password, userName, email from users where userId = ?",
                mapper,
                userId
        ).stream().findAny();
    }

    private final RowMapper<User> mapper = (rs, rowNum) -> {
        User user = new User(
                rs.getInt("id"),
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("userName"),
                rs.getString("email"));
        return user;
    };
}
