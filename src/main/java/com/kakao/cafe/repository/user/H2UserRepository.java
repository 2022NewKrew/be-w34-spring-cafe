package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.*;
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
        UserId userId = user.getUserId();
        Password password = user.getPassword();
        Name name = user.getName();
        Email email = user.getEmail();

        jdbcTemplate.update(
                "INSERT INTO USERS(USERID, PASSWORD, NAME, EMAIL) VALUES(?, ?, ?, ?)", userId.getValue(), password.getValue(), name.getValue(), email.getValue()
        );
    }

    @Override
    public void update(UserId id, Password password, User user) {
        jdbcTemplate.update(
                "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE USERID = ? AND PASSWORD = ?" , user.getName().getValue(), user.getEmail().getValue(), id.getValue(), password.getValue()
        );
    }

    @Override
    public User findById(UserId id) {
        return DataAccessUtils.singleResult(jdbcTemplate.query(
                "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE USERID = ?", rowMapper , id.getValue()
        ));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT USERID, PASSWORD, NAME, EMAIL FROM USERS", rowMapper
        );
    }
}
