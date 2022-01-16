package com.kakao.cafe.repository;

import com.kakao.cafe.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDBRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDBRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void signUp(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", user.getUserId());
        parameters.put("password", user.getPassword());
        parameters.put("name", user.getName());
        parameters.put("email", user.getEmail());

        simpleJdbcInsert.withTableName("USER_TABLE").execute(parameters);
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT USERID, PASSWORD, NAME, EMAIL FROM USER_TABLE", userRowMapper());
    }

    @Override
    public User findUserByUserId(String userId) {
        List<User> users = jdbcTemplate.query("SELECT USERID, PASSWORD, NAME, EMAIL FROM USER_TABLE WHERE USERID = ?", userRowMapper(), userId);
        return users.stream().findAny().orElse(null);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) ->
                new User(rs.getString("USERID"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"));
    }
}
