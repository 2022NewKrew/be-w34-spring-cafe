package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class H2UserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    public H2UserRepository(JdbcTemplate jdbcTemplate) {
        this(jdbcTemplate, new UserRowMapper());
    }

    public H2UserRepository(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(User user) {
        String userId = user.getUserId();
        String password = user.getPassword();
        String name = user.getName();
        String email = user.getEmail();

        jdbcTemplate.update(
                "INSERT INTO USERS(USERID, PASSWORD, NAME, EMAIL) VALUES(?, ?, ?, ?)", userId, password, name, email
        );
    }

    @Override
    public void update(String id, String password, User user) {
        jdbcTemplate.update(
                "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE USERID = ? AND PASSWORD = ?" , user.getName(), user.getEmail(), id, password
        );
    }

    @Override
    public User findById(String id) {
        return DataAccessUtils.singleResult(jdbcTemplate.query(
                "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE USERID = ?", rowMapper , id
        ));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS", rowMapper
        );
    }
}
