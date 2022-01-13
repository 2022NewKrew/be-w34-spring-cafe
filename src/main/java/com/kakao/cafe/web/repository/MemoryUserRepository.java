package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemoryUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(UserDTO userDTO) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            String sql = "INSERT INTO cafe_user (user_id, password, name, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userDTO.getUserId());
            statement.setString(2, userDTO.getPassword());
            statement.setString(3, userDTO.getName());
            statement.setString(4, userDTO.getEmail());
            return statement;
        }, holder);
        return getUserByUserId(userDTO.getUserId());
    }

    @Override
    public List<User> getUserList() {
        String sql = "SELECT * FROM cafe_user";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public User getUserByUserId(String userId) {
        String sql = "SELECT * FROM cafe_user WHERE user_id LIKE ?";
        return jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                userId);
    }

    public User update(UserDTO userUpdateDTO) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        System.out.println(userUpdateDTO);
        jdbcTemplate.update(con -> {
            String sql = "UPDATE cafe_user SET password = ?, name = ?, email = ? WHERE user_id = ?";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userUpdateDTO.getPassword());
            statement.setString(2, userUpdateDTO.getName());
            statement.setString(3, userUpdateDTO.getEmail());
            statement.setString(4, userUpdateDTO.getUserId());
            return statement;
        }, holder);
        return getUserByUserId(userUpdateDTO.getUserId());
    }
}
