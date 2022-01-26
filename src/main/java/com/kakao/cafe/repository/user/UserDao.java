package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<UserInfo> userMapper = (rs, rowNum) -> UserInfo.builder()
            .userId(rs.getString("userId"))
            .password(rs.getString("password"))
            .name(rs.getString("name"))
            .email(rs.getString("email"))
            .signUpDate(rs.getString("signUpDate"))
            .build();

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(UserInfo userInfo) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                userInfo.getUserId(), userInfo.getPassword(),
                userInfo.getName(), userInfo.getEmail(), userInfo.getSignUpDate());
    }

    public Optional<UserInfo> selectById(String id) {
        String sql = "SELECT userId,password,name,email,signUpDate FROM USERS WHERE userID=?";
        List<UserInfo> resultList = jdbcTemplate.query(sql, userMapper, id);
        return Optional.ofNullable(resultList.isEmpty() ? null : resultList.get(0));

    }

    public List<UserInfo> selectAll() {
        String sql = "SELECT userId,password,name,email,signUpDate FROM USERS";
        return jdbcTemplate.query(sql, (userMapper));
    }
}
