package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {
    private final Logger logger = LoggerFactory.getLogger(UserRepositoryJdbcImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User(
                rs.getString("email"),
                rs.getString("name"),
                rs.getString("password")
        );
        user.setId(rs.getLong("id"));
        user.setCreationTime(rs.getTimestamp("creation_time"));
        return user;
    };

    @Autowired
    public UserRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            insert(user);
        } else {
            update(user);
        }
    }

    private void insert(User user) {
        String INSERT_USER = "INSERT INTO USER_INFO (EMAIL, NAME, PASSWORD) " +
                "VALUES (?, ?, ?);";
        jdbcTemplate.update(INSERT_USER, user.getEmail(), user.getName(), user.getPassword());
    }

    private void update(User user) {
        String UPDATE_USER = "UPDATE USER_INFO " +
                "SET EMAIL=?, NAME=?, PASSWORD=? " +
                "WHERE ID=?";
        try {
            jdbcTemplate.update(UPDATE_USER, user.getEmail(), user.getName(), user.getPassword(), user.getId());
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        String SELECT_USERS = "SELECT ID, EMAIL, NAME, PASSWORD, CREATION_TIME FROM USER_INFO";
        return jdbcTemplate.query(SELECT_USERS, userMapper);
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        String SELECT_USER = "SELECT ID, EMAIL, NAME, PASSWORD, CREATION_TIME " +
                "FROM USER_INFO " +
                "WHERE ID=?";
        try {
            return jdbcTemplate.queryForObject(SELECT_USER, userMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("해당 아이디의 사용자가 없습니다.");
        }
    }

    @Override
    public User findByEmail(String email) throws NotFoundException {
        String SELECT_USER = "SELECT ID, EMAIL, NAME, PASSWORD, CREATION_TIME " +
                "FROM USER_INFO " +
                "WHERE EMAIL=?";
        try {
            return jdbcTemplate.queryForObject(SELECT_USER, userMapper, email);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("해당 이메일의 사용자가 없습니다.");
        }
    }
}
