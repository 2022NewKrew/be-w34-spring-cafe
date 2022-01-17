package com.kakao.cafe.user.repo;

import com.kakao.cafe.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("USER")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public long save(User target) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("USER_ID", target.getUserId())
                .addValue("NAME", target.getName())
                .addValue("EMAIL", target.getEmail());
        return (long) simpleJdbcInsert.executeAndReturnKey(parameterSource);
    }

    @Override
    public User fetch(long id) {
        String query = "SELECT * FROM `USER` WHERE ID = ?";
        List<User> users = jdbcTemplate.query(query, new UserRowMapper());
        return users.size() == 0 ? null : users.get(0);
    }

    @Override
    public List<User> fetchAll() {
        String query = "SELECT * FROM `USER`";
        return jdbcTemplate.query(query, new UserRowMapper());
    }

    @Override
    public int getUserCount() {
        String query = "SELECT COUNT(*) FROM `USER`";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return count == null ? 0 : count;
    }

    @Override
    public Optional<User> fetchByUserId(String userId) {
        String query = "SELECT * FROM `USER` WHERE USER_ID=?";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("USER_ID", userId);
        List<User> users = jdbcTemplate.query(query, new UserRowMapper(), parameterSource);
        return users.size() == 0 ? Optional.empty() : Optional.of(users.get(0));
    }
}
