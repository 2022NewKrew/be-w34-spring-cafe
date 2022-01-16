package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import com.kakao.cafe.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class UserDBRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDBRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void signUp(User user) {

    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public User findUserByUserId(String userId) {
        return null;
    }
}
