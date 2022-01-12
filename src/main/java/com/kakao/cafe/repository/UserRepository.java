package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
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
    public Optional<User> findById(Integer id) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM users");
        query.append(" WHERE users.id = ?");
        User user = jdbcTemplate.query(query.toString(), new Object[]{id}, (rs) -> {
            User entity = new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
            return entity;
        });
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT *");
        query.append(" FROM users");
        return jdbcTemplate.query(query.toString(), (rs, rowNum) -> {
            User entity = new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
            return entity;
        });
    }
}
