package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcTemplateUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcTemplateUserRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("userid");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getNickname());
        parameters.put("email", user.getEmail());
        parameters.put("password", user.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        user.setUserId(key.intValue());
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        List<User> result = jdbcTemplate.query("select * from users where userid = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = jdbcTemplate.query("select * from users where email = ?", memberRowMapper(), email);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", memberRowMapper());
    }

    private RowMapper<User> memberRowMapper(){
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("userid"));
            user.setNickname(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            return user;
        };
    }
}
