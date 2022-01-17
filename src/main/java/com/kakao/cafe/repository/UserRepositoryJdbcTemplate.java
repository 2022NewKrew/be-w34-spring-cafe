package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
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
public class UserRepositoryJdbcTemplate implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryJdbcTemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query("SELECT * FROM user", userRowMapper());
    }

    @Override
    public Optional<User> selectByKey(Long key) {
        List<User> result = jdbcTemplate.query("SELECT * FROM user where key = ?", userRowMapper(), key);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> selectById(String id) {
        List<User> result = jdbcTemplate.query("SELECT * FROM user where id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Long insert(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("key");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", user.getId());
        parameters.put("pw", user.getPw());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return key.longValue();
    }

    @Override
    public void update(Long key, User user) {
        jdbcTemplate.update("UPDATE user SET name = ?, pw = ?, email = ? WHERE key = ?",
                user.getName(), user.getPw(), user.getEmail(), key);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = User.builder()
                    .key(rs.getLong("key"))
                    .id(rs.getString("id"))
                    .pw(rs.getString("pw"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .build();
            return user;
        };
    }
}
