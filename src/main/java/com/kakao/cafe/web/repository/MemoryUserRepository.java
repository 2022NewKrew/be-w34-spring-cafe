package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

    public MemoryUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(UserDTO userDTO) {
        String sql = "INSERT INTO cafe_user (user_id, password, name, email) VALUES (:userId, :password, :name, :email)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userDTO);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
        return getUserByUserId(userDTO.getUserId());
    }

    @Override
    public List<User> getUserList() {
        String sql = "SELECT * FROM cafe_user";
        return namedParameterJdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public User getUserByUserId(String userId) {
        String sql = "SELECT * FROM cafe_user WHERE user_id LIKE :userId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", userId);
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, userRowMapper);
    }

    public User update(UserDTO userUpdateDTO) {
        String sql = "UPDATE cafe_user SET password = :password, name = :name, email = :email WHERE user_id = :userId";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userUpdateDTO);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
        return getUserByUserId(userUpdateDTO.getUserId());
    }
}
