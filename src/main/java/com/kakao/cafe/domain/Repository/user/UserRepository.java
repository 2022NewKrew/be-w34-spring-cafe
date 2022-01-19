package com.kakao.cafe.domain.Repository.user;

import com.kakao.cafe.domain.Entity.User;
import com.kakao.cafe.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public void save(User user) {
        this.jdbcTemplate.update("INSERT INTO USERS (userId, password, name, email) VALUES (?, ?, ?, ?)",
                user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public List<User> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM USERS", this.userMapper);
    }

    public boolean isUserIdExist(String userId) {
        try {
            this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE userId = ?", this.userMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    public User findById(String userId) {
        return this.jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE userId = ?", this.userMapper, userId);
    }

    public void update(String userId, String name, String email) {
        this.jdbcTemplate.update("UPDATE USERS SET name=?, email=? WHERE userId =?", name, email, userId);
    }


}
