package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into users(userId, password, name, email) values(?, ?, ?, ?)",
            user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public User findByUserId(String userId) {
        List<User> users = jdbcTemplate.query("select * from users where userId=?", mapper, userId);
        if (users.size() == 0) {
            throw new NoSuchElementException();
        }
        return users.get(0);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", mapper);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from users where true");
    }

    static RowMapper<User> mapper = (rs, rowNum) ->
        User.builder()
            .userId(rs.getString("userId"))
            .email(rs.getString("email"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .build();
}
