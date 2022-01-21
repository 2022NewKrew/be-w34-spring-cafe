package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO users (USERID, PASSWORD, NAME, EMAIL) VALUES ( ?, ?, ?, ?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET PASSWORD=?, NAME=?, EMAIL=? WHERE ID =?",
                user.getPassword(), user.getName(), user.getEmail(), user.getId());
    }

    @Override
    public Optional<User> selectById(Long id) {
        List<User> result =  jdbcTemplate.query("SELECT * FROM users WHERE id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> selectByUserId(String userId) {
        List<User> result =  jdbcTemplate.query("SELECT * FROM users WHERE userId = ?", userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper());
    }


    private RowMapper<User> userRowMapper(){
        return (rs, rowNum) -> {
            return User.builder()
                    .id(rs.getLong("id"))
                    .userId(rs.getString("userId"))
                    .password(rs.getString("password"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .build();
        };
    }
}
