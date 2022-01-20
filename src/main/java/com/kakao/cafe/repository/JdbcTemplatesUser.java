package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class JdbcTemplatesUser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplatesUser(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO USERS (user_id, password, name, email) VALUES( ?, ?, ?, ?)";
        jdbcTemplate.update((con) -> {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            return pstmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    public User findOneById(String userId) {
        String sql = "SELECT * FROM USERS WHERE user_id = ?";
        return jdbcTemplate.query(sql, userRowMapper(), userId).get(0);
    }

    public List<User> findOneByUserIdPassword(String userId, String password) {
        String sql = "SELECT * FROM USERS WHERE user_id = ? AND password = ?";
        return jdbcTemplate.query(sql, userRowMapper(), userId, password);
    }

    public void updateUser(User changeUser) {
        String sql = "UPDATE USERS SET name = ?, email = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, changeUser.getName(), changeUser.getEmail(), changeUser.getUserId());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User(
                    rs.getString("user_id"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("email")
            );
            user.setId(rs.getLong("id"));
            return user;
        };
    }

}
