package com.kakao.cafe.repository.jdbc;

import com.kakao.cafe.dto.user.UserCreateCommand;
import com.kakao.cafe.dto.user.UserListShow;
import com.kakao.cafe.dto.user.UserModifyCommand;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class UserJdbcRepository implements UserRepository {
    private static final String STORE_SQL =
            "INSERT INTO USERS(USER_ID, PASSWORD, NAME, EMAIL) VALUES(?, ?, ?, ?)";
    private static final String MODIFY_SQL =
            "UPDATE USERS SET PASSWORD=?, NAME=?, EMAIL=? WHERE USER_ID=?";
    private static final String DELETE_SQL =
            "DELETE FROM USERS WHERE USER_ID=?";
    private static final String SEARCH_SQL =
            "SELECT * FROM USERS WHERE USER_ID=?";
    private static final String TO_LIST_SQL =
            "SELECT * FROM USERS";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void store(UserCreateCommand ucc) {
        jdbcTemplate.update(STORE_SQL,
                ucc.getUserId(),
                ucc.getPassword(),
                ucc.getName(),
                ucc.getEmail());
    }

    @Override
    public void modify(String userId, UserModifyCommand umc) {
        jdbcTemplate.update(MODIFY_SQL,
                umc.getPassword(),
                umc.getName(),
                umc.getEmail(),
                userId);
    }

    @Override
    public void delete(String userId) {
        jdbcTemplate.update(DELETE_SQL, userId);
    }

    @Override
    public User search(String userId) {
        return jdbcTemplate.queryForObject(SEARCH_SQL, userRowMapper(), userId);
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate.query(TO_LIST_SQL, userRowMapper());
    }

    public RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getLong("USER_INDEX"),
                rs.getString("USER_ID"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getString("EMAIL")
        );
    }
}
