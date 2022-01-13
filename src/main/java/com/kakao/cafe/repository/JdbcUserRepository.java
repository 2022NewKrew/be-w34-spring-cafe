package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from article", userRowMapper());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> result = jdbcTemplate.query("select * from user where userId = ?", userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into user (userId, password, name, email) values (?, ?, ?, ?) on DUPLICATE key update name=?, email=?;",
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getName(),
                user.getEmail());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("userId"),
                    rs.getInt("password"),
                    rs.getString("name"),
                    rs.getString("email"));
            return user;
        };
    }
}
