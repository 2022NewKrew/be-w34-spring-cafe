package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Transactional
    public void save(User user) {
        String sql = "INSERT INTO USER (uid, password, name, email, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUid(), user.getPassword(), user.getName(), user.getEmail(),
            user.getCreatedAt());
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM USER";
        return jdbcTemplate.query(sql, this::userRowMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUid(String uid) {
        String sql = "SELECT * FROM USER WHERE uid = ?";
        return jdbcTemplate.query(sql, this::userRowMapper, uid).stream()
            .findFirst();
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
