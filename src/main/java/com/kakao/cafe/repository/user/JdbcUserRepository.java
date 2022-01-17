package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.user.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    private static final String INSERT_USER_QUERY = "INSERT INTO users(user_id,password,user_name,email) VALUES(?,?,?,?)";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET user_id=?, password=?, user_name=?, email=? WHERE id = ?";
    private static final String SELECT_USERS_QUERY = "SELECT id, user_id, password, user_name, email FROM users";
    private static final String SELECT_USER_BY_USER_ID_QUERY = "SELECT id, user_id, password, user_name, email FROM users WHERE user_id = ?";
    private static final String SELECT_USER_BY_ID_QUERY = "SELECT id, user_id, password, user_name, email FROM users WHERE id = ?";

    public Long insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_USER_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserName());
            ps.setString(4,user.getEmail());
            return ps;
        }, keyHolder);
        user.updateId(keyHolder.getKey().longValue());
        return user.getId();
    }

    @Override
    public Long update(User user) {
        jdbcTemplate.update(UPDATE_USER_QUERY, user.getUserId(), user.getPassword(), user.getUserName(), user.getEmail(), user.getId());
        return user.getId();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_USERS_QUERY, userRowMapper);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> users = jdbcTemplate.query(SELECT_USER_BY_USER_ID_QUERY, userRowMapper, userId);
        return Optional.ofNullable(users.isEmpty() ? null : users.get(0));
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> users = jdbcTemplate.query(SELECT_USER_BY_ID_QUERY, userRowMapper, id);
        return Optional.ofNullable(users.isEmpty() ? null : users.get(0));
    }

}
