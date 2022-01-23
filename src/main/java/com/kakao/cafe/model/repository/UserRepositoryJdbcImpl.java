package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository("UserRepositoryJdbcImpl")
public class UserRepositoryJdbcImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) ->
                User.builder()
                        .id(rs.getLong("ID"))
                        .userId(rs.getString("USER_ID"))
                        .password(rs.getString("PASSWORD"))
                        .name(rs.getString("NAME"))
                        .email(rs.getString("EMAIL")).build();
    }

    @Override
    public boolean saveUser(User user) {
        try {
            jdbcTemplate.update("INSERT INTO USERS (USER_ID, PASSWORD, NAME, EMAIL) VALUES ( ?, ?, ?, ? )",
                    user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT ID, USER_ID, PASSWORD, NAME, EMAIL FROM USERS",
                userRowMapper());
    }

    @Override
    public Optional<User> findUserById(Long id) {
        List<User> result = jdbcTemplate.query("SELECT ID, USER_ID, PASSWORD, NAME, EMAIL FROM USERS WHERE ID = ?",
                userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        List<User> result = jdbcTemplate.query("SELECT ID, USER_ID, PASSWORD, NAME, EMAIL FROM USERS WHERE USER_ID = ?",
                userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findUserByLoginInfo(String userId, String password) {
        List<User> result = jdbcTemplate.query("SELECT ID, USER_ID, PASSWORD, NAME, EMAIL FROM USERS WHERE USER_ID = ? AND PASSWORD = ?",
                userRowMapper(), userId, password);
        return result.stream().findAny();
    }

    @Override
    public boolean modifyUser(User user) {
        jdbcTemplate.update("UPDATE USERS SET PASSWORD = ?, NAME = ?, EMAIL = ? WHERE USER_ID = ?",
                user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
        return true;
    }

    @Override
    public boolean deleteUser(String userId, String password) {
        jdbcTemplate.update("DELETE FROM USERS WHERE USER_ID = ? AND PASSWORD = ?",
                userId, password);
        return true;
    }
}
