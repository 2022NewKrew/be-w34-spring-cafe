package com.kakao.cafe.web.repository;

import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Users> selectAllUsers() {
        return jdbcTemplate.query(QueryConstants.USER_SELECT, new UserMapper());
    }

    public Users insertUser(Users user) {
        jdbcTemplate.update(QueryConstants.USER_INSERT, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
        return jdbcTemplate.queryForObject(QueryConstants.USER_SELECT_BY_USERID, new UserMapper(), user.getUserId());
    }

    public Users selectByUserId(int id) {
        return jdbcTemplate.queryForObject(QueryConstants.USER_SELECT_BY_ID, new UserMapper(), id);
    }

    public Users selectByUserName(String userId) {
        return jdbcTemplate.queryForObject(QueryConstants.USER_SELECT_BY_USERID, new UserMapper(), userId);
    }

    public void updateUser(int id, Users updateUser) {
        jdbcTemplate.update(QueryConstants.USER_UPDATE, updateUser.getPassword(), updateUser.getName(), updateUser.getEmail(), id);
    }


}
