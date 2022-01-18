package com.kakao.cafe.web.service;

import com.kakao.cafe.UserMapper;
import com.kakao.cafe.domain.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private final JdbcTemplate jdbcTemplate;

    private UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Users> getUsers() {
        return jdbcTemplate.query(QueryConstants.userSelect, new UserMapper());
    }

    public Users addUser(Users user) {
        jdbcTemplate.update(QueryConstants.userInsert, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
        return jdbcTemplate.queryForObject(QueryConstants.userSelectByUserId, new UserMapper(), user.getUserId());
    }

    public Users getByUserId(int id) {
        return jdbcTemplate.queryForObject(QueryConstants.userSelectById, new UserMapper(), id);
    }

    public Users getByUserName(String userId) {
        return jdbcTemplate.queryForObject(QueryConstants.userSelectByUserId, new UserMapper(), userId);
    }

    public void updateUser(int id, Users updateUser, String newPassword) {
        if (!getByUserId(id).getPassword().equals((updateUser.getPassword())))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        if (!newPassword.isBlank())
            updateUser.setPassword(newPassword);
        jdbcTemplate.update(QueryConstants.userUpdate, updateUser.getPassword(), updateUser.getName(), updateUser.getEmail(), id);
    }

}
