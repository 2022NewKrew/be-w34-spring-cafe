package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String sql = "INSERT INTO USER (uid, password, name, email, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUid(), user.getPassword(), user.getName(), user.getEmail(),
            user.getCreatedAt());
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM USER";
        return jdbcTemplate.query(sql, this::userRowMapper);
    }

    @Override
    public Optional<User> findUserByUid(String uid) {
        String sql = "SELECT * FROM USER WHERE uid = ?";
        return jdbcTemplate.query(sql, this::userRowMapper, uid).stream()
            .findFirst();
    }

    @Override
    public void update(String uid, String name, String email) {
        String sql = "UPDATE USER SET name = ?, email = ? WHERE uid = ?";
        jdbcTemplate.update(sql, name, email, uid);
    }

    private User userRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return User.of(rs.getLong("id"),
            rs.getString("uid"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getTimestamp("created_at").toLocalDateTime());
    }
}
