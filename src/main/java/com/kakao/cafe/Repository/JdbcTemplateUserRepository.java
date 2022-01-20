package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository {

    DateTimeFormatter userTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveUser(User user) {
        jdbcTemplate.update(
                "insert into users(nickname, email, password) values(?,?,?)",
                user.getNickName(), user.getEmail(), user.getPassword());
    }

    @Override
    public Optional<User> findByUserId(Long id) {
        List<User> result = jdbcTemplate.query("select * from users where id = ?", userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByNickName(String nickName) {
        List<User> result = jdbcTemplate.query("select * from users where nickname = ?", userRowMapper(), nickName);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = jdbcTemplate.query("select * from users where email = ?", userRowMapper(), email);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("select * from users", userRowMapper());
    }

    @Override
    public void editUserInfo(Long userId, String newEmail, String newNickName) {
        jdbcTemplate.update(
                "update users set nickname= ?, email = ? where id = ?",
                newNickName, newEmail, userId);
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User(
                    rs.getString("email"),
                    rs.getString("Nickname"),
                    rs.getString("password"));
            user.setId(rs.getLong("id"));
            user.setCreated(rs.getTimestamp("created").toLocalDateTime().format(userTimeFormatter));
            return user;
        };
    }
}
