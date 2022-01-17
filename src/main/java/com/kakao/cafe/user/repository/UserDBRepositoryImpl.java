package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import com.kakao.cafe.user.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class UserDBRepositoryImpl  implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> find(String stringId) {
        String SQL = "SELECT * FROM USERS WHERE string_id = ?";
        User user = jdbcTemplate.queryForObject(SQL, new UserMapper(), stringId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> find(Long id) {
        String SQL = "SELECT * FROM USERS WHERE ID = ?";
        User user = jdbcTemplate.queryForObject(SQL, new UserMapper(), id);
        return Optional.ofNullable(user);
    }

    public ArrayList<User> findAll() {
        String SQL = "SELECT * FROM USERS";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return new ArrayList<User>(users);
    }

    @Override
    public Long persist(User user) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO USERS (string_id, password, name, email, sign_up_date) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getStringId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            return statement;
        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().longValue();
    }

    public void updateUserInfo(User user) {
        String SQL = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE string_id = ?";
        jdbcTemplate.update(SQL, user.getPassword(), user.getName(), user.getEmail(), user.getStringId());
    }
}
