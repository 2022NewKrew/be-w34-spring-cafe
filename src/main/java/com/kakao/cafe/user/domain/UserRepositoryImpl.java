package com.kakao.cafe.user.domain;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    @Override
    public void save(User user) {
        final String sql = "insert into users(email, password, nickname) values(?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getNickname());
    }

    @Override
    public Optional<User> findById(Long id) {
        final String sql = "select * from users where user_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String sql = "select * from users where email = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        final String sql = "select exists "
            + "(select user_id from users where email=? limit 1) "
            + "as success";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, email));
    }

    @Override
    public boolean existsByNickname(String nickname) {
        final String sql = "select exists "
            + "(select user_id from users where nickname=? limit 1) "
            + "as success";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, nickname));
    }

    @Override
    public List<User> findAll() {
        final String sql = "select * from users";
        return jdbcTemplate.query(sql, userMapper);
    }
}
