package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.exception.UserNotExistException;
import com.kakao.cafe.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class UserJdbcRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getId(), user.getUserId()
                , user.getName(), user.getPassword(),
                user.getEmail());
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcTemplate.query(sql, userRowMapper());
    }

    @Override
    public Long getNumberOfUsers() {
        String sql = "SELECT count(*) FROM USERS";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Optional<User> findOneByUserId(String userId) {
        try{
            String sql = "SELECT * FROM USERS WHERE userId=?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, userRowMapper(), userId));
        }catch (DataAccessException e){
            return Optional.empty();
        }
    }

    public RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("userId"),
                rs.getString("name"),
                rs.getString("password"),
                rs.getString("email")
        );
    }
}
