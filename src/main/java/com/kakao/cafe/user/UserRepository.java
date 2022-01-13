package com.kakao.cafe.user;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.Users;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final RowMapper<User> userRowMapper = getUserMapper();
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

    private RowMapper<User> getUserMapper() {
        return (resultSet, rowNumber) -> new User(
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4)
        );
    }
}
