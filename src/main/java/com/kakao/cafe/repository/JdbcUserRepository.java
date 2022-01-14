package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from `user`", userRowMapper());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> result = jdbcTemplate.query("select * from `user` where userId = ?", userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into `user` (userId, password, name, email) values (?, ?, ?, ?)",
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail());
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(
                "update `user` set name = ?, email = ? where userId = ?",
                user.getName(),
                user.getEmail(),
                user.getUserId());
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
