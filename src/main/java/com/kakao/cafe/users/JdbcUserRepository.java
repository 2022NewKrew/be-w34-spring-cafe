package com.kakao.cafe.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void update(User user) {
        System.out.println("TODO");
    }

    @Override
    public Optional<User> findById(long id) {
        System.out.println("TODO");
        return Optional.of(null);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("TODO");
        return Optional.of(null);
    }

}
