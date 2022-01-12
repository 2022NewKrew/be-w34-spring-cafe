package com.kakao.cafe.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-12 012
 * Time: 오후 4:16
 */
@Repository
public class H2UserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public H2UserRepository(DataSource dataSource) {
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
}
