package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = getUserRowMapper();

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (email, nickname, password, created_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getNickname(), user.getPassword(), user.getCreatedDate());
        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql, userRowMapper, email).stream().findFirst();
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, userRowMapper, id).stream().findFirst();
    }

    public RowMapper<User> getUserRowMapper(){
        return (rs, rowNum) -> new User(
                rs.getLong("id"),
            rs.getString("email"),
            rs.getString("nickname"),
            rs.getString("password"),
            rs.getTimestamp("created_date").toLocalDateTime());
    }
}
