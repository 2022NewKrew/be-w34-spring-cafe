package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserCreateDTO;
import com.kakao.cafe.web.utility.CombinedSqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

    public MemoryUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(UserCreateDTO userCreateDTO) {
        String sql = "INSERT INTO cafe_user (user_id, password, name, email, image_path, created_time, modified_time) " +
                "VALUES (:userId, :password, :name, :email, :image_path, :created_time, :modified_time)";
        CombinedSqlParameterSource sqlParameterSource = new CombinedSqlParameterSource(userCreateDTO);
        sqlParameterSource.addValue("image_path", "/static/images/anonymous-user.png");
        sqlParameterSource.addValue("created_time", new Timestamp(System.currentTimeMillis()));
        sqlParameterSource.addValue("modified_time", new Timestamp(System.currentTimeMillis()));
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
        return getUserByUserId(userCreateDTO.getUserId());
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

    public User update(UserCreateDTO userUpdateDTO) {
        String sql = "UPDATE cafe_user SET password = :password, name = :name, email = :email WHERE user_id = :userId";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(userUpdateDTO);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
        return getUserByUserId(userUpdateDTO.getUserId());
    }
}
