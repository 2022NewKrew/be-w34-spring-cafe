package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserH2Repository implements UserRepository {

    private final RowMapper<User> userRowMapper = getUserMapper();
    private final JdbcTemplate jdbcTemplate;

    public UserH2Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        String sql = "INSERT INTO users (user_id, password, name, email) values (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            user.getUserId(),
            user.getPassword(),
            user.getName(),
            user.getEmail()
        );
    }

    public Users findAll() {
        String sql = "SELECT * FROM users";
        return new Users(jdbcTemplate.query(sql, userRowMapper));
    }

    public User findById(String userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, userId);
    }

    @Override
    public User findByIdAndPassword(String userId, String password) {
        String sql = "SELECT * FROM users WHERE user_id = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, userId, password);
    }

    private RowMapper<User> getUserMapper() {
        return (resultSet, rowNumber) -> new User(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4)
        );
    }
}
