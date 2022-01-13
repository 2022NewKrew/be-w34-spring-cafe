package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.List;
import java.util.Optional;
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
    public Optional<User> findByUserId(String userId) {
        List<User> users = jdbcTemplate.query("select * from users where userId=?", mapper, userId);
        return users.size() == 0 ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", mapper);
    }

    static RowMapper<User> mapper = (rs, rowNum) ->
        User.builder()
            .userId(rs.getString("userId"))
            .email(rs.getString("email"))
            .name(rs.getString("name"))
            .password(rs.getString("password"))
            .build();
}
