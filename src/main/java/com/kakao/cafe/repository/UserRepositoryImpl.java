package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public User save(User user) {
        String sql = "INSERT INTO WRITER (PASSWORD, NAME, EMAIL, CREATEDTIME)"+
                    " VALUES (:password, :name, :email, :createdTime)";

        Map<String, Object> param = new HashMap<>();
        param.put("password", user.getPassword());
        param.put("name", user.getName());
        param.put("email", user.getEmail());
        param.put("createdTime", user.getCreatedTime());

        this.namedParameterJdbcTemplate.update(sql, param);

        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL, CREATEDTIME FROM WRITER";
        return namedParameterJdbcTemplate.query(sql, userRowMapper());
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL, CREATEDTIME FROM WRITER WHERE USER_ID = :userId";
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        return namedParameterJdbcTemplate.queryForObject(sql, param, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            return new User(
                    rs.getInt("USER_ID"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getString("EMAIL"),
                    rs.getDate("CREATED_TIME").toLocalDate()
            );
        };
    }
}
