package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;
import com.kakao.cafe.user.dto.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsers() {
        String sql = "select * from users";

        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User getUserByCondition(String key, String value) {
        String sql = String.format("select * from users where %s = '%s'", key, value);
        return (User) jdbcTemplate.query(sql, new UserRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {

        jdbcTemplate.update("INSERT INTO users(userid,password,name,email) VALUES (?,?,?,?)",
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
        );

    }

    @Override
    public void updateUser(User updatedUser) {
        String sql = String.format("UPDATE users SET name='%s', password='%s', email='%s' WHERE userid='%s'", updatedUser.getName(), updatedUser.getPassword(), updatedUser.getEmail(), updatedUser.getUserId());
        jdbcTemplate.update(sql);
    }


}
