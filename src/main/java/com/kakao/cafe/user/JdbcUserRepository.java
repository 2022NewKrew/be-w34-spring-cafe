package com.kakao.cafe.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-12 012
 * Time: 오후 4:16
 */
@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User insert(User user) {
        String sql = "INSERT INTO USERS (USER_ID, PASSWORD, NAME, EMAIL)" +
                    " VALUES (?, ?, ?, ?)";

            jdbcTemplate.update(sql,
                    user.getUserId(),
                    user.getPassword(),
                    user.getName(),
                    user.getEmail());

            return user;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE USERS SET" +
                " NAME=?, EMAIL=? WHERE ID = ?";

        jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getId());

        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findUserById(Integer id) {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    @Override
    public User findUserByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE USER_ID = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), userId);
    }
}
