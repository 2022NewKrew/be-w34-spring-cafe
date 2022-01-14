package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository{
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void init() {
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("USER").usingGeneratedKeyColumns("ID");
    }

    @Override
    @Transactional(readOnly = false)
    public User save(User user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        Number key = jdbcInsert.executeAndReturnKey(param);
        user.setId(key.intValue());
        this.jdbcTemplate.update(
                "INSERT INTO WRITER (PASSWORD, NAME, EMAIL, CREATEDTIME) VALUES (?, ?, ?, ?)",
                user.getPassword(), user.getName(), user.getEmail(), user.getCreatedTime()
        );
        return user;
    }

    @Override
    public Optional<List<User>> findAll() {
        String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL, CREATEDTIME FROM WRITER";
        List<User> result = jdbcTemplate.query(sql, userRowMapper());
        return Optional.of(result);
    }

    @Override
    public User findById(String userId) {
        String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL, CREATEDTIME FROM WRITER WHERE USER_ID = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper(), userId);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User(
                    rs.getInt("USER_ID"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getString("EMAIL"),
                    rs.getDate("CREATED_TIME").toLocalDate()
            );
            return user;
        };
    }
}
