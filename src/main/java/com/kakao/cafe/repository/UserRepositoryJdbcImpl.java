package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User(
                rs.getString("email"),
                rs.getString("name"),
                rs.getString("password")
        );
        user.setId(rs.getLong("id"));
        user.setCreationTime(rs.getDate("creation_time"));
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
                "VALUES ('%s', '%s', '%s');";
        jdbcTemplate.execute(String.format(
                INSERT_USER, user.getEmail(), user.getName(), user.getPassword())
        );
    }

    private void update(User user) {
        String UPDATE_USER = "UPDATE USER_INFO " +
                "SET EMAIL='%s', NAME='%s', PASSWORD='%s' " +
                "WHERE ID=%d";
        jdbcTemplate.execute(String.format(
                UPDATE_USER, user.getEmail(), user.getName(), user.getPassword(), user.getId()
        ));
    }

    @Override
    public List<User> findAll() {
        String SELECT_USERS = "SELECT ID, EMAIL, NAME, PASSWORD, CREATION_TIME FROM USER_INFO";
        return jdbcTemplate.query(SELECT_USERS, userMapper);
    }

    @Override
    public Optional<User> findById(Long id) {
        String SELECT_USER = "SELECT ID, EMAIL, NAME, PASSWORD, CREATION_TIME " +
                "FROM USER_INFO " +
                "WHERE ID=%d";
        List<User> repositoryUsers = jdbcTemplate.query(String.format(SELECT_USER, id), userMapper);
        return repositoryUsers.stream().findFirst();
    }
}
