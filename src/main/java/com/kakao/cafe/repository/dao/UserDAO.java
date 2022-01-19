package com.kakao.cafe.repository.dao;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(User user) {
        String sql = "INSERT INTO USERS (USER_ID,PASSWORD,EMAIL,REGISTER_DATE) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getEmail(), user.getRegisterDate());
    }

    public User findByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE USER_ID=?";
        return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> User.newInstance(rs.getLong("id"),
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getString("register_date"));
    }

}
