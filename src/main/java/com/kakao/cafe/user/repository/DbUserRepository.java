package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DbUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DbUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("MEMBER").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", user.getUserId());
        parameters.put("password", user.getPassword());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.longValue());
        return user;
    }

    @Override
    public Optional<User> findOneById(Long id) {
        return jdbcTemplate.query("select * from member where id = ?", userRowMapper(), id)
                .stream()
                .findAny();
    }

    @Override
    public Optional<User> findOneByUserId(String userId) {
        return jdbcTemplate.query("select * from member where userId = ?", userRowMapper(), userId)
                .stream()
                .findAny();
    }

    @Override
    public List<User> findAll() {
        List<User> users = jdbcTemplate.query("select * from member", userRowMapper());
        return users;
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("update member set password = ?, name = ?, email = ?", user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public void clear() {
        jdbcTemplate.update("delete from member");
    }


    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserId(rs.getString("userId"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        };
    }
}
