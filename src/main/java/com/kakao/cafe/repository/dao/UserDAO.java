package com.kakao.cafe.repository.dao;

import com.kakao.cafe.domain.user.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //USERS TABLE의 ID는 USERS TABLE의 AUTO_INCREMENT 옵션으로 자동 생성.
    public void create(User user) {
        String sql = "INSERT INTO USERS (USER_ID,PASSWORD,EMAIL,REGISTER_DATE) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getEmail(), user.getRegisterDate());
    }

    public Optional<User> findByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE USER_ID=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper(), userId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> User.builder()
                .id(rs.getLong("id"))
                .userId(rs.getString("user_id"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .registerDate(rs.getString("register_date"))
                .build();
    }
}
