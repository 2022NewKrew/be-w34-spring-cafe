package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("jdbcUserRepository")
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("insert into users(userId,password,userName,email) VALUES(?,?,?,?)");
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserName());
            ps.setString(4,user.getEmail());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Long updateUser(User user) {
        return null;
    }
}
