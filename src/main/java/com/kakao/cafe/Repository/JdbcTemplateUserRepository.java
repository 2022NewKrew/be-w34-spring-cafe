package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository{

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
        List<User> result = jdbcTemplate.query("select * from users where id = ?",userRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByNickName(String nickName) {
        List<User> result = jdbcTemplate.query("select * from users where nickname = ?",userRowMapper(), nickName);
        return result.stream().findAny();
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("select * from users",userRowMapper());
    }

    private RowMapper<User> userRowMapper(){
        return (rs, rowNum) -> {
            User user = new User(
                    rs.getString("email"),
                    rs.getString("Nickname"),
                    rs.getString("password"));
            user.setId(rs.getLong("id"));
            user.setCreated(rs.getTimestamp("created"));
            return user;
        };
    }
}
