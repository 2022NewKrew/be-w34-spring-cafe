package com.kakao.cafe.domain.user.dao;

import com.kakao.cafe.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        final String sql = "INSERT INTO `USER` (`user_id`, `password`, `nickname`, `email`, `created_at`) VALUES(?, ?, ?, ?, ?)";

        Object[] parameters = {
                user.getUserId(),
                user.getPassword(),
                user.getNickname(),
                user.getEmail(),
                LocalDate.now()
        };

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void update(User user) {
        final String sql = "UPDATE `USER` SET `nickname` = ?, `email` = ? WHERE `id` = ?";
        int result = jdbcTemplate.update(sql, user.getNickname(), user.getEmail(), user.getId());
    }

    @Override
    public Optional<User> findById(Long id) {
        final String sql = "SELECT `id`, `user_id`, `password`, `nickname`, `email`, `created_at` from `USER` where `id` = ?";
        List<User> result = jdbcTemplate.query(sql, userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        final String sql = "SELECT `id`, `user_id`, `password`, `nickname`, `email`, `created_at` from `USER` where `user_id` = ?";
        List<User> result = jdbcTemplate.query(sql, userRowMapper(), userId);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        final String sql = "SELECT `id`, `user_id`, `password`, `nickname`, `email`, `created_at` from `USER`";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (resultSet, rowNum) -> new User(
                resultSet.getLong("id"),
                resultSet.getString("user_id"),
                resultSet.getString("password"),
                resultSet.getString("nickname"),
                resultSet.getString("email"),
                resultSet.getDate("created_at").toLocalDate()
        );
    }
}
