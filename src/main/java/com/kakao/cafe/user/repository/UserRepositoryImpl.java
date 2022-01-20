package com.kakao.cafe.user.repository;

import com.kakao.cafe.global.util.JdbcUtil;
import com.kakao.cafe.user.persistence.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcUtil<User> jdbcUtil;

    @Override
    public void save(User user) {
        String sql = "INSERT INTO USERS(user_id, password, name, email) VALUES(?, ?, ?, ?)";
        jdbcUtil.writeRecord(sql,
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE id = ?";
        jdbcUtil.writeRecord(sql,
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getId()
        );
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = "SELECT * FROM USERS WHERE user_id = ?";
        return jdbcUtil.readRecord(sql, userId);
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM USERS WHERE id = ?";
        return jdbcUtil.readRecord(sql, id);
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        String sql = "SELECT * FROM USERS WHERE user_id = ?, password = ?";
        return jdbcUtil.readRecord(sql, userId, password);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USERS";
        return jdbcUtil.readRecords(sql);
    }
}
