package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
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

@Repository
@Primary
@RequiredArgsConstructor
public class UserDBRepositoryImpl  implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public User find(Long id) {
        String SQL = "SELECT * FROM USERS WHERE ID = ?";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper(), id);
        return users.get(0);
    }

    public ArrayList<User> findAll() {
        String SQL = "SELECT * FROM USERS";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return new ArrayList<User>(users);
    }

    public Long persist(UserCreateRequestDTO dto) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO USERS (user_string_id, password, name, email, sign_up_date) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dto.getStringId());
            statement.setString(2, dto.getPassword());
            statement.setString(3, dto.getNickName());
            statement.setString(4, dto.getEmail());
            statement.setTimestamp(5, Timestamp.valueOf(dto.getSignUpDate()));
            return statement;
        }, generatedKeyHolder);

        return generatedKeyHolder.getKey().longValue();
    }

    public Long findDBIdById(String stringId) {
        String SQL = "SELECT * FROM USERS WHERE user_string_id = ?";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper(), stringId);
        return users.get(0).getId();
    }

    public String findStringIdByDBId(Long id) {
        String SQL = "SELECT * FROM USERS WHERE id = ?";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper(), id);
        return users.get(0).getStringId();
    }

    public String findPasswordByDBId(Long userId) {
        String SQL = "SELECT * FROM USERS WHERE id = ?";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper(), userId);
        return users.get(0).getPassword();
    }

    public void updateUserInfo(UserUpdateRequestDTO dto) {
        String SQL = "UPDATE USERS SET password = ?, name = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(SQL, new Object[]{dto.getNewPassword(), dto.getName(), dto.getEmail(), dto.getUserId()});
    }
}
