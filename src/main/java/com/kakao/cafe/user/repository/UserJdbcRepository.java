package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserCreateDTO;
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

        return jdbcTemplate.query("select * from users",
                (rs, rn) ->
        {User user = new User(rs.getString("userid"), rs.getString("password"), rs.getString("name"), rs.getString("email"), rs.getLong("sequence"));
            return user;});
    }

    @Override
    public void addUser(UserCreateDTO userCreateDTO) {

        jdbcTemplate.update("INSERT INTO users(userid,password,name,email) VALUES (?,?,?,?)",
                userCreateDTO.getUserId(),
                userCreateDTO.getPassword(),
                userCreateDTO.getName(),
                userCreateDTO.getEmail()
        );

    }
}
