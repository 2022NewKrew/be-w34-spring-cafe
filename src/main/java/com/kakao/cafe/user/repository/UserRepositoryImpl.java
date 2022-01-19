package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper;

    @Override
    public void save(User user) {
        String sql = "INSERT INTO USERS(user_id, password, name, email) VALUES(?, ?, ?, ?)";
        writeRecord(sql,
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE id = ?";
        writeRecord(sql,
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getId()
        );
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE user_id = ?";
        return readRecord(sql, userId);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM USERS WHERE id = ?";
        return readRecord(sql, id);
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        String sql = "SELECT * FROM USERS WHERE user_id = ?, password = ?";
        return readRecord(sql, userId, password);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return readRecords(sql);
    }

    private Optional<User> readRecord(String sql, Object... params) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, params));
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private List<User> readRecords(String sql, Object... params) {
        return jdbcTemplate.query(sql, rowMapper, params);
    }

    private void writeRecord(String sql, Object... params) {
        jdbcTemplate.update(sql, params);
    }
}
