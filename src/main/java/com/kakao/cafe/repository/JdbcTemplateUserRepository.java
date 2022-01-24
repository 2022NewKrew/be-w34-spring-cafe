package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateUserRepository implements RepositoryInterface<User> {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("users").usingGeneratedKeyColumns("userid");

        Map<String, Object> parameters = makeParameters(user);
        Number userid = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setUserId(userid.longValue());
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> user = jdbcTemplate.query("select * from users where userid = ?", userRowMapper(), id);
        return user.stream().findAny();
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> user = jdbcTemplate.query("select * from users where email = ?", userRowMapper(), name);
        return user.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", userRowMapper());
    }

    @Override
    public User update(User user) {
        jdbcTemplate.update("update users set name = ?, password = ?, email = ? where userid = ?",
                user.getName(), user.getPassword(), user.getEmail(), user.getUserId());
        return user;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from users where userid = ?", id);
    }

    private RowMapper<User> userRowMapper() {
        return (resultSet, rowNum) -> {
            User user = new User();
            user.setUserId(resultSet.getLong("userid"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            return user;
        };
    }

    private Map<String, Object> makeParameters(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userid", user.getUserId());
        parameters.put("name", user.getName());
        parameters.put("password", user.getPassword());
        parameters.put("email", user.getEmail());
        return parameters;
    }
}
