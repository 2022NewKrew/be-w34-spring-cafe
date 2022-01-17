package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<UserInfo> userMapper = (rs, rowNum) -> new UserInfo(rs.getString("userId"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("signUpDate"));

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(UserInfo userInfo) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                userInfo.getUserId(), userInfo.getPassword(),
                userInfo.getName(), userInfo.getEmail(), userInfo.getSignUpDate());
    }

    public UserInfo selectById(String id) {
        String sql = "SELECT userId,password,name,email,signUpDate FROM USERS WHERE userID=?";
        return jdbcTemplate.queryForObject(sql, userMapper, id);
    }

    public List<UserInfo> selectAll() {
        String sql = "SELECT userId,password,name,email,signUpDate FROM USERS";
        return jdbcTemplate.query(sql, (userMapper));
    }
}
