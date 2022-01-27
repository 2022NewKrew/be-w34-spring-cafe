package com.kakao.cafe.repository;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
public class UserJdbcRepository implements UserRepository {

    public final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into users(userId, password, userName, email) values (?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getEmail());
            return ps;
        }, keyHolder);

    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM users ORDER BY id DESC",
                mapper
        );
    }

    @Override
    public Optional<User> findOne(Integer id) {
        return jdbcTemplate.query(
                "select user_id, userId, password, userName, email from users where user_id = ?",
                mapper,
                id
        ).stream().findAny();
    }

    public Optional<User> findByUserId(String userId) {
        return jdbcTemplate.query(
                "select user_id, userId, password, userName, email from users where userId = ?",
                mapper,
                userId
        ).stream().findAny();
    }


    public void update(Integer id, UserDto userDto) {
        jdbcTemplate.update(
                "update users set password = ?, userName = ?, email = ? where id = ?",
                userDto.getPassword(),
                userDto.getUserName(),
                userDto.getEmail(),
                id
        );
    }

    private final RowMapper<User> mapper = (rs, rowNum) -> {
        User user = new User(
                rs.getInt("user_id"),
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("userName"),
                rs.getString("email"));
        return user;
    };
}
