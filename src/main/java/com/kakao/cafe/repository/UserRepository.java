package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements CrudRepository<User, Integer> {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User entity) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO users(user_id, password, name, email)");
        query.append(" VALUES (?, ?, ?, ?)");

        int update = jdbcTemplate.update(query.toString(), entity.getUserId(), entity.getPassword(), entity.getName(), entity.getEmail());
        if (update > 0) {
            return entity;
        }

        return null;
    }

    @Override
    public User update(User entity) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE users SET user_id = ?, password = ?, name = ?, email = ? WHERE users.id = ?");

        int update = jdbcTemplate.update(query.toString(), entity.getUserId(), entity.getPassword(), entity.getName(), entity.getEmail(), entity.getId());
        if (update > 0) {
            return entity;
        }

        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM users");
        query.append(" WHERE users.id = ?");

        User user = jdbcTemplate.queryForObject(query.toString(), (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("name"), rs.getString("email")), id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByUserId(String userId) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM users");
        query.append(" WHERE users.user_id = ?");

        User user;
        try {
            user = jdbcTemplate.queryForObject(query.toString(), (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("name"), rs.getString("email")), userId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM users");

        return jdbcTemplate.query(query.toString(), (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("name"), rs.getString("email")));
    }

    @Override
    public boolean delete(User entity) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM users WHERE users.id = ?");
        int delete = jdbcTemplate.update(query.toString(), entity.getId());
        if (delete > 0) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
}
