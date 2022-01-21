package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NoSuchUser;
import com.kakao.cafe.repository.dto.UserResult;
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
public class UserJdbcRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("create table users (id varchar(15) PRIMARY KEY, email varchar(40), name varchar(20), password varchar(30))");
    }

    @Override
    public void save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getUserId());
        parameters.put("name", user.getName());
        parameters.put("password", user.getPassword());
        parameters.put("email", user.getEmail());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", userRowMapper());
    }

    @Override
    public Optional<User> findById(String userId) {
        List<User> query = jdbcTemplate.query("select * from users where id=?", userRowMapper(), userId);

        return query.stream().findAny();
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("update users set name=?, email=? where id = ?", user.getName(), user.getEmail(), user.getUserId());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            UserResult userResult = new UserResult();
            userResult.setUserId(rs.getString("id"));
            userResult.setEmail(rs.getString("email"));
            userResult.setName(rs.getString("name"));
            userResult.setPassword(rs.getString("password"));
            return User.from(userResult);
        };
    }
}
