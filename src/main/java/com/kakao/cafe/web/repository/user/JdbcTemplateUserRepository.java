package com.kakao.cafe.web.repository.user;

import com.kakao.cafe.web.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void save(User user) {
        String sql = "insert into users (`user_id`, `password`, `name`, `email`) values(:userId,:password,:name,:email)";
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(sql, paramSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        List<User> result = jdbcTemplate.query("select * from users where `id` = :id", param, userRowMapper());
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        SqlParameterSource param = new MapSqlParameterSource("userId", userId);
        List<User> result = jdbcTemplate.query("select * from users where `user_id` = :userId", param, userRowMapper());
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void update(User user) {
        String sql = "update `users` set `user_id` = :userId, `name` = :name, `email` = :email, `password` = :password where `id` = :id";
        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        jdbcTemplate.update(sql, paramSource);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserId(rs.getString("user_id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            return user;
        };
    }
}
