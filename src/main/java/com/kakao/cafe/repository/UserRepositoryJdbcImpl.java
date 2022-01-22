package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
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
        String INSERT_USER = "INSERT INTO user_info (email, name, password) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(INSERT_USER, user.getEmail(), user.getName(), user.getPassword());
    }

    private void update(User user) {
        String UPDATE_USER = "UPDATE user_info " +
                "SET email=?, name=?, password=? " +
                "WHERE id=?";
        try {
            jdbcTemplate.update(UPDATE_USER, user.getEmail(), user.getName(), user.getPassword(), user.getId());
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        String SELECT_USERS = "SELECT id, email, name, password, creation_time " +
                "FROM user_info";
        return jdbcTemplate.query(SELECT_USERS, userMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        String SELECT_USER = "SELECT id, email, name, password, creation_time " +
                "FROM user_info " +
                "WHERE id=?";
        return jdbcTemplate.query(SELECT_USER, userMapper, id)
                .stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String SELECT_USER = "SELECT id, email, name, password, creation_time " +
                "FROM user_info " +
                "WHERE email=?";
        return jdbcTemplate.query(SELECT_USER, userMapper, email)
                .stream().findFirst();
    }
}
